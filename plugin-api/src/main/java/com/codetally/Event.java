package com.codetally;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//An event comes from github, gitlab, or sourcex. it does not know about this service.
//somehow it must express what it is, and how it will affect charges.
//it cannot know what a "charge" is.
//it can know who,what,when,where
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String host; //github.com, bitbucket, etc

    private String projectKey;
    //who
    private String author;
    //what
    private String description; //somehow this is more or less the link to the charge.
    //when
    private Date created;
    //where
    private String src; //a url to the source of this event. This might be a github commit, for example.
    //override
    private double overrideValue; //forget the charges, I am TELLING you how much to charge.

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
}
