package com.codetally.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;
    private String projectname;
    private String projectkey;
    private String codetally;
    private String currencycode;
    private String projecturl;

    private List<Charge> charges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectkey() {
        return projectkey;
    }

    public void setProjectkey(String projectkey) {
        this.projectkey = projectkey;
    }

    public String getCodetally() {
        return codetally;
    }

    public void setCodetally(String codetally) {
        this.codetally = codetally;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String getProjecturl() {
        return projecturl;
    }

    public void setProjecturl(String projecturl) {
        this.projecturl = projecturl;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }
}
