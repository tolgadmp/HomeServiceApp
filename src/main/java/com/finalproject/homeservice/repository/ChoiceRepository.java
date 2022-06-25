package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.Choice;
import com.finalproject.homeservice.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> getChoicesByAttributes(Attribute attribute);

    //List<Choice> getChoicesByJobs(Job job);




}
