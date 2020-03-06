package com.github;

import com.codetally.plugin.Configuration;
import com.codetally.plugin.Event;
import com.codetally.plugin.EventAdapter;
import com.github.model.Commit;
import com.github.model.GithubEvent;
import com.google.gson.Gson;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GithubAdapter implements EventAdapter {
    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public List<Event> getEvents(String body) {
        List<Event> returnEvents = new ArrayList<>();

        Gson gson = new Gson();
        GithubEvent githubEvent = gson.fromJson(body, GithubEvent.class);
        if (githubEvent==null)
            return returnEvents;

        for (Commit commit : githubEvent.getCommits()) {
            Event commitEvent = new Event();
            commitEvent.setAuthor(commit.getAuthor().getEmail());
            commitEvent.setCreated(Date.valueOf(commit.getTimestamp()));
            commitEvent.setDescription(commit.getMessage());
            commitEvent.setHost("github.com");
            commitEvent.setProjectKey(githubEvent.getRepository().getHtmlUrl());
            commitEvent.setSrc(commit.getUrl());
            returnEvents.add(commitEvent);
        }
        return returnEvents;
    }
}
