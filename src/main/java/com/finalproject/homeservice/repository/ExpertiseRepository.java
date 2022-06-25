package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertiseRepository extends JpaRepository<Expertise,Long> {

    Expertise findExpertiseByExpertiseName(String expertiseName);
    Expertise findExpertiseById(long id);
}
