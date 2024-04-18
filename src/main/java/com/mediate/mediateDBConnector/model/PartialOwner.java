package com.mediate.mediateDBConnector.model;

public class PartialOwner {
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

    String name;
    String relationship;
}
