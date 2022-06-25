package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Job;
import com.finalproject.homeservice.entity.JobAttributeChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAttributeChoiceRepository extends JpaRepository<JobAttributeChoice, Long> {

    List<JobAttributeChoice> getJobAttributeChoiceByJob(Job job);
}
