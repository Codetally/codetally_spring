package codetally.service;

import codetally.model.Charge;
import codetally.model.Project;
import codetally.model.Transaction;
import com.codetally.plugin.Event;
import com.codetally.plugin.EventAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    private ChargeService chargeService;

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
                        Project project = projectService.getByKey(event.getProjectKey()); //will get or insert project
                        List<Charge> chargeList = chargeService.calculateCharges(project, event);
                        Transaction transaction = chargeService.calculateTransaction(event, chargeList);
                        project.getTransactions().add(transaction);
                        projectService.save(project);
                    });
                });
    }
}
