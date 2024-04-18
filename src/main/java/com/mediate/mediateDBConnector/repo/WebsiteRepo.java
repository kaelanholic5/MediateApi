package com.mediate.mediateDBConnector.repo;

import com.mediate.mediateDBConnector.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface WebsiteRepo extends JpaRepository<Website, Long>{

    @Query(value="SELECT * FROM website w WHERE w.url = :url", nativeQuery=true)
    public Website getWebsiteByUrl(@Param("url") String url);

    @Query(value="SELECT * FROM website w WHERE w.id = :id", nativeQuery=true)
    public Website getWebsiteById(@Param("id") Integer id);

}
