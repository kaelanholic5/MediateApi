package com.mediate.mediateDBConnector.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partialcompanyownership")
public class PartialOwnerRelationships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "partialownerid")
    private Integer partialOwnerId;

    @Column(name = "ownedcompanyid")
    private Integer ownedCompanyId;

    @Column(name = "relationship")
    private String relationship;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartialOwnerId() {
        return partialOwnerId;
    }

    public void setPartialOwnerId(Integer partialOwnerId) {
        this.partialOwnerId = partialOwnerId;
    }

    public Integer getOwnedCompanyId() {
        return ownedCompanyId;
    }

    public void setOwnedCompanyId(Integer ownedCompanyId) {
        this.ownedCompanyId = ownedCompanyId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

}
