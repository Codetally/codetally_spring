package com.codetally.service;

import com.codetally.model.Logline;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public interface LogService {
    public String getLogLinesByOwnerAndRepo(String owner, String repo);

    public Logline createLogline(String level, String message);
    public boolean addSingle(Logline logline, long repositoryId);
    public boolean resetLog(long repositoryId);
}
