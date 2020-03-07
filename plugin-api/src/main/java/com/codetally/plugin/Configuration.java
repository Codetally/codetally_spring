package com.codetally.plugin;

public class Configuration {
    private String author;
    private boolean isActive;
    private String src;
    private String description;
    private String version;
    private String logoUrl;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
