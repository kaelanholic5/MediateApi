package com.mediate.mediateDBConnector.repo;

import com.mediate.mediateDBConnector.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long>{

    @Query(value="SELECT * FROM people p WHERE p.id = :id", nativeQuery=true)
    public Person getPersonById(@Param("id") Integer id);
}
