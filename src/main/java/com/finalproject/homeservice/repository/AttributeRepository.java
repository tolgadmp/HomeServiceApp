package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.Choice;
import com.finalproject.homeservice.entity.Job;
import com.finalproject.homeservice.entity.JobDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    List<Attribute> getAttributesByJobDefinitions(JobDefinition jobDefinition);

    List<Attribute> getAttributesByChoices(Choice choice);

}