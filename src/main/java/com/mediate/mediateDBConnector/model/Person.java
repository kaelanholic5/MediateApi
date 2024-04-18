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
@Table(name = "people")
public class Person {

    public Person() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public List<PersonCompanyRelation> getCompanyRelationships() {
        return companyRelationships;
    }

    public void setCompanyRelationships(List<PersonCompanyRelation> companyRelationships) {
        this.companyRelationships = companyRelationships;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "person")
    private String personName;

    @Column(name = "description")
    private String description;

    @Column(name = "wiki")
    private String wikiLink;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personid", referencedColumnName = "id")
    private List<PersonCompanyRelation> companyRelationships;
}
