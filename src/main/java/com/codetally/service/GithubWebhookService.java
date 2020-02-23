package com.codetally.service;

import com.codetally.model.github.Commit;
import com.codetally.model.github.GithubEvent;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by greg on 25/06/17.
 */
public class GithubWebhookService {

    public void addSingle(InputStream inputStream) throws UnsupportedEncodingException {

        Gson gson = new Gson();
        GithubEvent githubEvent = gson.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), GithubEvent.class);

        RepositoryService repositoryService = new RepositoryService();
        repositoryService.addSingle(githubEvent.getRepository());

        String owner = githubEvent.getRepository().getOwner().getLogin();
        if (owner == null) {
            owner = githubEvent.getRepository().getOwner().getName();
        }
        String repo = githubEvent.getRepository().getName();

        long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);

        LogService logService = new LogService();
        logService.resetLog(repositoryId);

        ChargeService chargeService = new ChargeService();
        chargeService.synchChargesByOwnernameAndRepo(owner, repo);

        CommitService commitService = new CommitService();

        for (Commit commit : githubEvent.getCommits()) {
            long startTime = System.nanoTime();
            logService.addSingle(logService.createLogline(LogService.INFO, "Processing a commit for author: " + commit.getAuthor().getEmail()), repositoryId);
            logService.addSingle(logService.createLogline(LogService.INFO, "The timestamp for commit " + commit.getId() + " is " + commit.getTimestamp()), repositoryId);
            float chargeamount = 0f;
            if (commitService.isTimelog(commit)) {
                logService.addSingle(logService.createLogline(LogService.INFO, "Commit type is TIMELOG"), repositoryId);
                chargeamount = chargeService.calculateTimeCharge(commit, repositoryId, owner, repo);
            } else {
                logService.addSingle(logService.createLogline(LogService.INFO, "Commit type is CHARGE"), repositoryId);
                chargeamount = chargeService.calculateAuthorCharge(commit, repositoryId);
                chargeamount = chargeService.calculateTaxCharge(repositoryId, chargeamount);
            }
            logService.addSingle(logService.createLogline(LogService.INFO, "The total amount is " + chargeamount), repositoryId);

            long elapsedTime = System.nanoTime() - startTime;
            double elapsedSeconds = (double) elapsedTime / 1000000000.0;
            commitService.addSingle(commit, repositoryId, chargeamount, String.valueOf(elapsedSeconds));
        }
        float codecost = commitService.getRepoCodecost(repositoryId);
        logService.addSingle(logService.createLogline(LogService.INFO, "The current repo cost is " + codecost), repositoryId);

        repositoryService.setCodecost(repositoryId, codecost);
    }
}
