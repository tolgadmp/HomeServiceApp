package com.finalproject.homeservice.jobDefinitionTest;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.repository.AttributeRepository;
import com.finalproject.homeservice.repository.JobDefinitionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class JobDefinitionRepositoryTest {

    @Autowired
    private JobDefinitionRepository jobDefinitionRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    @Test
    public void getJobDefinitionByCategoryId(){
        long id = 2;
        List<JobDefinition> jobDefinitionList =
                jobDefinitionRepository.getJobDefinitionByCategoryId(id);
        jobDefinitionList.forEach(System.out::println);
    }

    @Test
    public void getJobDefinitionWithAttributes(){
        long id = 2;
        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        List<Attribute> attributes = attributeRepository.getAttributesByJobDefinitions(jobDefinition);
        attributes.forEach(System.out::println);
    }

    @Test
    public void getJobDefinitionByAttribute(){
        long id = 2;
       Attribute attribute =  attributeRepository.getById(id);
        List<JobDefinition> jobDefinitions = jobDefinitionRepository.getJobDefinitionsByAttributes(attribute);
        jobDefinitions.forEach(System.out::println);
    }
}
