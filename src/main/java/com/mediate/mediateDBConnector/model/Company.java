package com.mediate.mediateDBConnector.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "companies")
public class Company {
    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PersonCompanyRelation> getPersonRelationships() {
        return personRelationships;
    }

    public void setPersonRelationships(List<PersonCompanyRelation> personRelationships) {
        this.personRelationships = personRelationships;
    }

    public List<CompanyOwnership> getParentCompanyRelationships() {
        return parentCompanyRelationships;
    }

    public void setParentCompanyRelationships(List<CompanyOwnership> parentCompanyRelationships) {
        this.parentCompanyRelationships = parentCompanyRelationships;
    }

    public List<CompanyOwnership> getChildCompanyRelationships() {
        return childCompanyRelationships;
    }

    public void setChildCompanyRelationships(List<CompanyOwnership> childCompanyRelationships) {
        this.childCompanyRelationships = childCompanyRelationships;
    }

    public List<PartialOwnerRelationships> getPartialOwners() {
        return partialOwners;
    }

    public void setPartialOwners(List<PartialOwnerRelationships> partialOwners) {
        this.partialOwners = partialOwners;
    }

    public List<PartialOwnerRelationships> getPartialOwnerships() {
        return partialOwnerships;
    }

    public void setPartialOwnerships(List<PartialOwnerRelationships> partialOwnerships) {
        this.partialOwnerships = partialOwnerships;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company")
    private String companyName;

    @Column(name = "wiki")
    private String wikiLink;

    @Column(name = "location")
    private String location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyid", referencedColumnName = "id")
    private List<PersonCompanyRelation> personRelationships;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "childcompanyid", referencedColumnName = "id")
    private List<CompanyOwnership> parentCompanyRelationships;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentcompanyid", referencedColumnName = "id")
    private List<CompanyOwnership> childCompanyRelationships;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ownedcompanyid", referencedColumnName = "id")
    private List<PartialOwnerRelationships> partialOwners;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "partialownerid", referencedColumnName = "id")
    private List<PartialOwnerRelationships> partialOwnerships;

}
