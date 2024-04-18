package com.mediate.mediateDBConnector.repo;

import com.mediate.mediateDBConnector.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CompanyRepo extends JpaRepository<Company, Long>{

    @Query(value="SELECT * FROM companies c WHERE c.id = :id", nativeQuery=true)
    public Company getCompanyById(@Param("id") Integer id);
}
