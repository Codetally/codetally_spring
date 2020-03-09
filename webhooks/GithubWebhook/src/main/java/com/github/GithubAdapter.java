package com.github;

import com.codetally.plugin.Configuration;
import com.codetally.plugin.Event;
import com.codetally.plugin.EventAction;
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
            for (String File : commit.getAdded()) {
                Event event = getCommitEvent(githubEvent.getRepository().getHtmlUrl(), commit, EventAction.added);
                returnEvents.add(event);
            }
            for (String File : commit.getModified()) {
                Event event = getCommitEvent(githubEvent.getRepository().getHtmlUrl(), commit, EventAction.modified);
                returnEvents.add(event);
            }
            for (String File : commit.getRemoved()) {
                Event event = getCommitEvent(githubEvent.getRepository().getHtmlUrl(), commit, EventAction.removed);
                returnEvents.add(event);
            }
        }
        return returnEvents;
    }

    private Event getCommitEvent(String projectUrl, Commit commit, EventAction eventAction) {
        Event commitEvent = new Event();
        commitEvent.setAuthor(commit.getAuthor().getEmail());
        commitEvent.setCreated(Date.valueOf(commit.getTimestamp()));
        commitEvent.setDescription(commit.getMessage());
        commitEvent.setHost("github.com");
        commitEvent.setProjectKey(projectUrl);
        commitEvent.setSrc(commit.getUrl());
        commitEvent.setRef(commit.getAuthor().getEmail()); //this is a link to the charge.
        commitEvent.setEventAction(eventAction);
        return commitEvent;
    }
}
