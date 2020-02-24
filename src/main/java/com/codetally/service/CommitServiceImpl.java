package com.codetally.service;

import com.codetally.model.CalculationResult;
import com.codetally.model.github.Commit;
import com.codetally.model.github.Repository;
import com.codetally.repository.CommitRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 */
@Service
public class CommitServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(CommitServiceImpl.class);

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private RepositoryService repositoryService;

    public boolean isTimelog(Commit commit) {
        for (String addFile : commit.getAdded()) {
            if (addFile.equalsIgnoreCase("timelog.json"))
                return true;
        }
        for (String addFile : commit.getModified()) {
            if (addFile.equalsIgnoreCase("timelog.json"))
                return true;
        }
        return false;
    }

    public void addSingle(Commit commit, long repositoryId, float codecost, String elapsedTime) {

        try {
            commitRepository.addSingle(commit, repositoryId, codecost, elapsedTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getRepoCodecost(long repositoryId) {
        try {
            return commitRepository.getRepoCodecost(repositoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0f;
    }

    public String getCurrent(String owner, String repo) {

        long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);
        Repository repository = repositoryService.getSingleByOwnerAndRepo(owner, repo);

        CalculationResult calculationResult = new CalculationResult();

        try {
            List<Commit> commitList = commitRepository.getCommits(repositoryId, 1);
            if (commitList.size() < 1) {
                return new Gson().toJson(calculationResult);
            }
            Commit commit = commitList.get(0);
            calculationResult = Commit2CalculationResult(commit);
            calculationResult.setHtmlUrl(repository.getHtmlUrl());
            calculationResult.setRepoTotalCost(String.valueOf(commitRepository.getRepoCodecost(repositoryId)));
            Currency currency = repositoryService.getCurrency(repositoryId);
            calculationResult.setCalculationCurrency(currency.getCurrencyCode());
            calculationResult.setElapsedTime(commitRepository.getElapsedTime(commit.getId()));
            calculationResult.setCalculationValue(commitRepository.getCommitCodecost(commit.getId()));
            calculationResult.setCalculationDate(commitRepository.getCalculationDate(commit.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Gson().toJson(calculationResult);
    }

    public String getHistory(String owner, String repo) {

        long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);
        Repository repository = repositoryService.getSingleByOwnerAndRepo(owner, repo);
        List<CalculationResult> calculationResultList = new ArrayList<>();

        try {
            List<Commit> commitList = commitRepository.getCommits(repositoryId, 10);
            for (Commit commit : commitList) {
                CalculationResult calculationResult = Commit2CalculationResult(commit);
                calculationResult.setHtmlUrl(repository.getHtmlUrl());
                Currency currency = repositoryService.getCurrency(repositoryId);
                calculationResult.setCalculationCurrency(currency.getCurrencyCode());
                float historicalCost = commitRepository.getRepoCodecostByDate(repositoryId, calculationResult.getCommitDate());
                calculationResult.setRepoTotalCost(String.valueOf(historicalCost));
                calculationResult.setElapsedTime(commitRepository.getElapsedTime(commit.getId()));
                calculationResult.setCalculationValue(commitRepository.getCommitCodecost(commit.getId()));
                calculationResult.setCalculationDate(commitRepository.getCalculationDate(commit.getId()));
                calculationResultList.add(calculationResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(calculationResultList);
    }

    private CalculationResult Commit2CalculationResult(Commit commit) {
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setAuthorName(commit.getAuthor().getName());
        calculationResult.setCommitterName(commit.getCommitter().getName());
        calculationResult.setCommitDate(commit.getTimestamp());
        calculationResult.setCommitUrl(commit.getUrl());
        calculationResult.setCommitId(commit.getId());
        calculationResult.setMessage(commit.getMessage());
        return calculationResult;
    }
}
