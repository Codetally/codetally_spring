package com.codetally.service;

import com.codetally.model.Logline;
import com.codetally.repository.LogRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by greg on 29/06/17.
 */
@Service
public class LogServiceImpl implements LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogRepository logRepository;

    public static final String INFO = "INFO";
    public static final String WARN = "WARNING";
    public static final String ERROR = "ERROR";
    public static final String DEBUG = "DEBUG";

    @Override
    public String getLogLinesByOwnerAndRepo(String owner, String repo) {

        RepositoryServiceImpl repositoryService = new RepositoryServiceImpl();
        long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);
        List<Logline> loglineList = null;
        try {
            loglineList = logRepository.getAllLoglines(repositoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(loglineList);
    }
    @Override
    public Logline createLogline(String level, String message) {
        DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));

        Calendar cal = Calendar.getInstance();
        String timestamp = df.format(cal.getTime());
        Logline logline = new Logline();
        logline.setLevel(level);
        logline.setMessage(message);
        logline.setTimestamp(timestamp);
        return logline;
    }

    @Override
    public boolean addSingle(Logline logline, long repositoryId) {
        logRepository.addLogLine(logline, repositoryId);
        return true;
    }
    @Override
    public boolean resetLog(long repositoryId) {
        try {
            logRepository.resetLog(repositoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
