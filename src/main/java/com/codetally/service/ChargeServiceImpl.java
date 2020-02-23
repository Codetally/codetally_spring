package com.codetally.service;

import com.codetally.configuration.GithubConfiguration;
import com.codetally.model.Charge;
import com.codetally.model.Codecost;
import com.codetally.model.HourlyRate;
import com.codetally.model.Timelog;
import com.codetally.model.github.Commit;
import com.codetally.repository.ChargeRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Currency;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 */
@Service
public class ChargeServiceImpl implements ChargeService{
    private static final Logger logger = LoggerFactory.getLogger(ChargeServiceImpl.class);

    @Autowired
    private ChargeRepository chargeRepository;

    @Override
    public void synchChargesByOwnernameAndRepo(String ownername, String repo) {
        try {
            RepositoryService repositoryService = new RepositoryService();
            long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(ownername, repo);

            LogService logService = new LogService();

            chargeRepository.deleteAllCharges(repositoryId);
            chargeRepository.deleteAllHourlyRates(repositoryId);

            String urlParameters = GithubConfiguration.client_id
                    + "=" + GithubConfiguration.clientid
                    + "&" + GithubConfiguration.client_secret
                    + "=" + GithubConfiguration.clientsecret;

            String request = GithubConfiguration.repos_url + ownername + "/" + repo + "/" + GithubConfiguration.contents + "/codetally.json?" + urlParameters;

            URL url = new URL(request);

            if (url != null) {
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setRequestMethod("GET");
                urlconnection.setRequestProperty("accept", GithubConfiguration.rawMIME);
                urlconnection.connect();
                Gson gson = new Gson();
                Codecost codecost = gson.fromJson(new InputStreamReader(urlconnection.getInputStream(), StandardCharsets.UTF_8), Codecost.class);
                logService.addSingle(logService.createLogline(LogService.INFO, "The currency found was: " + codecost.getCurrency()), repositoryId);

                try {
                    Currency currency = Currency.getInstance(codecost.getCurrency().toUpperCase());
                    logService.addSingle(logService.createLogline(LogService.INFO, "The currency symbol found was: " + currency.getSymbol()), repositoryId);
                    repositoryService.setCurrency(repositoryId, currency);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                for (Charge charge : codecost.getCharges()) {
                    chargeRepository.addCharge(charge, repositoryId);
                }
                for (HourlyRate hourlyRate : codecost.getHourlyRates()) {
                    chargeRepository.addHourlyRate(hourlyRate, repositoryId);
                }
                urlconnection.disconnect();
            }
        } catch (Exception e) {
            // common log spam. oftn repos will lack  codetally file. only way to defend is to create one on the fly...
            //e.printStackTrace();
        }
    }
    @Override
    public float calculateAuthorCharge(Commit commit, long repositoryId) {
        LogService logService = new LogService();
        float codecost = 0f;
        try {
            for (String addedFile : commit.getAdded()) {
                logService.addSingle(logService.createLogline(LogService.INFO, "An added record was found for " + addedFile), repositoryId);
                Charge addedCharge = chargeRepository.getSingle(commit.getAuthor().getEmail(), "added", repositoryId);
                if (addedCharge.getChargeamount().isEmpty()) {
                    logService.addSingle(logService.createLogline(LogService.WARN, "AUTHORAMOUNT not found for " + addedFile), repositoryId);
                } else {
                    codecost += Float.parseFloat(addedCharge.getChargeamount());
                }
            }
            for (String modifiedFile : commit.getModified()) {
                logService.addSingle(logService.createLogline(LogService.INFO, "A modified record was found for " + modifiedFile), repositoryId);
                Charge modifiedCharge = chargeRepository.getSingle(commit.getAuthor().getEmail(), "modified", repositoryId);
                if (modifiedCharge.getChargeamount().isEmpty()) {
                    logService.addSingle(logService.createLogline(LogService.WARN, "AUTHORAMOUNT not found for " + modifiedCharge), repositoryId);
                } else {
                    codecost += Float.parseFloat(modifiedCharge.getChargeamount());
                }
            }
            for (String removedFile : commit.getRemoved()) {
                logService.addSingle(logService.createLogline(LogService.INFO, "A removed record was found for " + removedFile), repositoryId);
                Charge removedCharge = chargeRepository.getSingle(commit.getAuthor().getEmail(), "removed", repositoryId);
                if (removedCharge.getChargeamount().isEmpty()) {
                    logService.addSingle(logService.createLogline(LogService.WARN, "AUTHORAMOUNT not found for " + removedCharge), repositoryId);
                } else {
                    codecost += Float.parseFloat(removedCharge.getChargeamount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logService.addSingle(logService.createLogline(LogService.INFO, "An exception occurred calculating author charges: " + e.getMessage()), repositoryId);
        }
        return codecost;
    }
    @Override
    public float calculateTaxCharge(long repositoryId, float chargeamount) {
        LogService logService = new LogService();
        logService.addSingle(logService.createLogline(LogService.INFO, "Looking up tax charges."), repositoryId);
        float codecost = chargeamount;
        try {

            List<Charge> chargeList = chargeRepository.getAllTaxCharges("commit", "any", repositoryId);
            logService.addSingle(logService.createLogline(LogService.INFO, "Found " + chargeList.size() + " tax charges."), repositoryId);
            for (Charge charge : chargeList) {
                logService.addSingle(logService.createLogline(LogService.INFO, "Found tax charge " + charge.getDescription() + " of type " + charge.getCalculationtype() + " and amount " + charge.getChargeamount()), repositoryId);
                if (charge.getCalculationtype().equalsIgnoreCase("percent")) {
                    codecost = codecost * (Float.parseFloat(charge.getChargeamount()) + 100) / 100;
                } else if (charge.getCalculationtype().equalsIgnoreCase("flat")) {
                    codecost += Float.parseFloat(charge.getChargeamount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logService.addSingle(logService.createLogline(LogService.INFO, "An exception occurred calculating tax charges: " + e.getMessage()), repositoryId);
        }
        return codecost;
    }
    @Override
    public float calculateTimeCharge(Commit commit, long repositoryId, String owner, String repo) {
        float timecost = 0f;
        try {

            String urlParameters = GithubConfiguration.client_id
                    + "=" + GithubConfiguration.clientid
                    + "&" + GithubConfiguration.client_secret
                    + "=" + GithubConfiguration.clientsecret;

            String request = GithubConfiguration.repos_url + owner + "/" + repo + "/" + GithubConfiguration.contents + "/timelog.json?" + urlParameters;

            URL url = new URL(request);

            if (url != null) {
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setRequestMethod("GET");
                urlconnection.setRequestProperty("accept", GithubConfiguration.rawMIME);
                urlconnection.connect();

                Gson gson = new Gson();
                Timelog timelog = gson.fromJson(new InputStreamReader(urlconnection.getInputStream(), StandardCharsets.UTF_8), Timelog.class);
                HourlyRate hourlyRate = chargeRepository.getHourlyRate(commit.getAuthor().getEmail(), repositoryId);

                float totalhours = Float.parseFloat(timelog.getHours()) + (Float.parseFloat(timelog.getMinutes()) / 60) + (Float.parseFloat(timelog.getSeconds()) / 60 / 60);
                LogService logService = new LogService();
                logService.addSingle(logService.createLogline(LogService.INFO, "Total hours worked is " + totalhours), repositoryId);
                timecost = totalhours * Float.parseFloat(hourlyRate.getCostPerHour());
                logService.addSingle(logService.createLogline(LogService.INFO, "Total cost for hours worked is " + timecost), repositoryId);

                urlconnection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timecost;
    }
    @Override
    public String getChargeConfig(String owner, String repo) {
        Codecost codecost = new Codecost();
        try {
            RepositoryService repositoryService = new RepositoryService();
            long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);

            codecost.setCurrency(repositoryService.getCurrency(repositoryId).getCurrencyCode());
            codecost.setCharges(chargeRepository.getAllCharges(repositoryId));
            codecost.setHourlyRates(chargeRepository.getAllHourlyRates(repositoryId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(codecost);
    }
}
