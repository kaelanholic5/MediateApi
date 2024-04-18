package com.mediate.mediateDBConnector.repo;

import com.mediate.mediateDBConnector.model.PersonCompanyRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonCompanyRelationRepo extends JpaRepository<PersonCompanyRelation, Long>{

    @Query(value="SELECT * FROM personcompanyrelation r WHERE r.personid = :personId AND r.companyid = :companyId", nativeQuery=true)
    public PersonCompanyRelation getRelationshipByCompanyAndPerson(@Param("personId") Integer personId, @Param("companyId") Integer companyId);
}