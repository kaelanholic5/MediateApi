package com.mediate.mediateDBConnector.model;

import java.util.List;

public class MainPerson {
    private String name;
    private String relationship;
    private List<String> otherCompanies;
    private String description;
    private String link;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public List<String> getOtherCompanies() {
        return otherCompanies;
    }

    public void setOtherCompanies(List<String> otherCompanies) {
        this.otherCompanies = otherCompanies;
    }

}
