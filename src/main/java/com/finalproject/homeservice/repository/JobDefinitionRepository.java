package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.JobDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobDefinitionRepository extends JpaRepository<JobDefinition, Long> {

    @Query("SELECT j FROM JobDefinition j where j.category.id = ?1")
    List<JobDefinition> getJobDefinitionByCategoryId(long id);

}
