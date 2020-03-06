package codetally.service;

import codetally.configuration.GithubConfiguration;
import codetally.model.Charge;
import codetally.model.Codecost;
import codetally.model.HourlyRate;
import codetally.model.Timelog;
import codetally.model.github.Commit;
import codetally.model.github.Repository;
import codetally.repository.ChargeRepository;
import com.codetally.model.*;
import com.codetally.plugin.Event;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 * CRUD for charge "templates"
 */
@Service
public class ChargeService {
    private static final Logger logger = LoggerFactory.getLogger(ChargeService.class);

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private LogService logService;

    public void synchChargesByOwnernameAndRepo(String ownername, String repo) {
        try {
            Repository repository = projectService.getSingleByOwnerAndRepo(ownername, repo);

            chargeRepository.deleteAllByRepository(repository);
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
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "The currency found was: " + codecost.getCurrency()), repositoryId);

                try {
                    Currency currency = Currency.getInstance(codecost.getCurrency().toUpperCase());
                    logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "The currency symbol found was: " + currency.getSymbol()), repositoryId);
                    projectService.setCurrency(repositoryId, currency);
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
    public List<Charge> calculateCharges(Event event) {
        List<Charge> returnCharges = new ArrayList<>();
        return returnCharges;
    }
    public float calculateAuthorCharge(Commit commit, long repositoryId) {

        float codecost = 0f;
        try {
            for (String addedFile : commit.getAdded()) {
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "An added record was found for " + addedFile), repositoryId);
                Charge addedCharge = chargeRepository.getSingle(commit.getAuthor().getEmail(), "added", repositoryId);
                if (addedCharge.getChargeamount().isEmpty()) {
                    logService.addSingle(logService.createLogline(LogServiceImpl.WARN, "AUTHORAMOUNT not found for " + addedFile), repositoryId);
                } else {
                    codecost += Float.parseFloat(addedCharge.getChargeamount());
                }
            }
            for (String modifiedFile : commit.getModified()) {
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "A modified record was found for " + modifiedFile), repositoryId);
                Charge modifiedCharge = chargeRepository.getSingle(commit.getAuthor().getEmail(), "modified", repositoryId);
                if (modifiedCharge.getChargeamount().isEmpty()) {
                    logService.addSingle(logService.createLogline(LogServiceImpl.WARN, "AUTHORAMOUNT not found for " + modifiedCharge), repositoryId);
                } else {
                    codecost += Float.parseFloat(modifiedCharge.getChargeamount());
                }
            }
            for (String removedFile : commit.getRemoved()) {
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "A removed record was found for " + removedFile), repositoryId);
                Charge removedCharge = chargeRepository.getSingle(commit.getAuthor().getEmail(), "removed", repositoryId);
                if (removedCharge.getChargeamount().isEmpty()) {
                    logService.addSingle(logService.createLogline(LogServiceImpl.WARN, "AUTHORAMOUNT not found for " + removedCharge), repositoryId);
                } else {
                    codecost += Float.parseFloat(removedCharge.getChargeamount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "An exception occurred calculating author charges: " + e.getMessage()), repositoryId);
        }
        return codecost;
    }
    public float calculateTaxCharge(long repositoryId, float chargeamount) {

        logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Looking up tax charges."), repositoryId);
        float codecost = chargeamount;
        try {

            List<Charge> chargeList = chargeRepository.getAllTaxCharges("commit", "any", repositoryId);
            logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Found " + chargeList.size() + " tax charges."), repositoryId);
            for (Charge charge : chargeList) {
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Found tax charge " + charge.getDescription() + " of type " + charge.getCalculationtype() + " and amount " + charge.getChargeamount()), repositoryId);
                if (charge.getCalculationtype().equalsIgnoreCase("percent")) {
                    codecost = codecost * (Float.parseFloat(charge.getChargeamount()) + 100) / 100;
                } else if (charge.getCalculationtype().equalsIgnoreCase("flat")) {
                    codecost += Float.parseFloat(charge.getChargeamount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "An exception occurred calculating tax charges: " + e.getMessage()), repositoryId);
        }
        return codecost;
    }
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
                LogServiceImpl logService = new LogServiceImpl();
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Total hours worked is " + totalhours), repositoryId);
                timecost = totalhours * Float.parseFloat(hourlyRate.getCostPerHour());
                logService.addSingle(logService.createLogline(LogServiceImpl.INFO, "Total cost for hours worked is " + timecost), repositoryId);

                urlconnection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timecost;
    }
    public String getChargeConfig(String owner, String repo) {
        Codecost codecost = new Codecost();
        try {

            long repositoryId = projectService.getSingleIdByOwnerAndRepo(owner, repo);

            codecost.setCurrency(projectService.getCurrency(repositoryId).getCurrencyCode());
            codecost.setCharges(chargeRepository.getAllCharges(repositoryId));
            codecost.setHourlyRates(chargeRepository.getAllHourlyRates(repositoryId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(codecost);
    }
}
