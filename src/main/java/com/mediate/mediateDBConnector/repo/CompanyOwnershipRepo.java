package com.mediate.mediateDBConnector.repo;

import com.mediate.mediateDBConnector.model.CompanyOwnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CompanyOwnershipRepo  extends JpaRepository<CompanyOwnership, Long>{
}
