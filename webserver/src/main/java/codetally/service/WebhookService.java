package codetally.service;

import codetally.model.Charge;
import codetally.model.Project;

import com.codetally.plugin.Event;
import com.codetally.plugin.EventAdapter;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 */
@Service
public class WebhookService {
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private LogService logService;

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private CommitService commitService;

    @Autowired
    @Qualifier("eventAdapterListFactoryBean")
    Object eventAdapters;

    public void processWebhook(String body) {

        ((List<EventAdapter>) eventAdapters)
                .forEach(eventAdapter ->
                {
                    System.out.println("Sending in raw data");
                    List<Event> eventList = eventAdapter.getEvents(body);
                    eventList.forEach(event -> {
                        Project project = projectService.getByKey(event.getProjectKey());
                        List<Charge> chargeList = chargeService.calculateCharges(event);
                        project.getCharges().addAll(chargeList);
                        projectService.save(project);
                    });
                });
    }

    public void addSingle(InputStream inputStream) throws UnsupportedEncodingException {

        Gson gson = new Gson();
        GithubEvent githubEvent = gson.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), GithubEvent.class);

        projectService.addSingle(githubEvent.getRepository());

        String owner = githubEvent.getRepository().getOwner().getLogin();
        if (owner == null) {
            owner = githubEvent.getRepository().getOwner().getName();
        }
        String repo = githubEvent.getRepository().getName();

        Repository repository = projectService.getSingleByOwnerAndRepo(owner, repo);

        logService.resetLog(repository);

        chargeService.synchChargesByOwnernameAndRepo(owner, repo);

        for (Commit commit : githubEvent.getCommits()) {
            long startTime = System.nanoTime();
            logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Processing a commit for author: " + commit.getAuthor().getEmail()), repositoryId);
            logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "The timestamp for commit " + commit.getId() + " is " + commit.getTimestamp()), repositoryId);
            float chargeamount = 0f;
            if (commitService.isTimelog(commit)) {
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Commit type is TIMELOG"), repositoryId);
                chargeamount = chargeService.calculateTimeCharge(commit, repositoryId, owner, repo);
            } else {
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Commit type is CHARGE"), repositoryId);
                chargeamount = chargeService.calculateAuthorCharge(commit, repositoryId);
                chargeamount = chargeService.calculateTaxCharge(repositoryId, chargeamount);
            }
            logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "The total amount is " + chargeamount), repositoryId);

            long elapsedTime = System.nanoTime() - startTime;
            double elapsedSeconds = (double) elapsedTime / 1000000000.0;
            commitService.addSingle(commit, repositoryId, chargeamount, String.valueOf(elapsedSeconds));
        }
        float codecost = commitService.getRepoCodecost(repositoryId);
        logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "The current repo cost is " + codecost), repositoryId);

        projectService.setCodecost(repositoryId, codecost);
    }
}
