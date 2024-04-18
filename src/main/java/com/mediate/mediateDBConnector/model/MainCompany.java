package com.mediate.mediateDBConnector.model;

import java.util.List;

public class MainCompany {
    private String name;
    private List<MainPerson> mainPeople;
    private List<String> subsidiaries;
    private String location;
    private List<PartialOwner> partialOwners;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MainPerson> getMainPeople() {
        return mainPeople;
    }

    public void setMainPeople(List<MainPerson> mainPeople) {
        this.mainPeople = mainPeople;
    }

    public List<String> getSubsidiaries() {
        return subsidiaries;
    }

    public void setSubsidiaries(List<String> subsidiaries) {
        this.subsidiaries = subsidiaries;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PartialOwner> getPartialOwners() {
        return partialOwners;
    }

    public void setPartialOwners(List<PartialOwner> partialOwners) {
        this.partialOwners = partialOwners;
    }


}
