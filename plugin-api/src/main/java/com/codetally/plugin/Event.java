package com.codetally.plugin;

import java.util.Date;

public class Event {

    private Long id;
    private String host; //model.com, bitbucket, etc

    private String projectKey;
    //who
    private String author;
    //what
    private String description; //somehow this is more or less the link to the charge.
    //when
    private Date created;
    //where
    private String src; //a url to the source of this event. This might be a model commit, for example.

    private String ref; //the name of the file added.
    //override
    private double overrideValue; //forget the charges, I am TELLING you how much to charge.

    private EventAction eventAction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public double getOverrideValue() {
        return overrideValue;
    }

    public void setOverrideValue(double overrideValue) {
        this.overrideValue = overrideValue;
    }

    public EventAction getEventAction() {
        return eventAction;
    }

    public void setEventAction(EventAction eventAction) {
        this.eventAction = eventAction;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
