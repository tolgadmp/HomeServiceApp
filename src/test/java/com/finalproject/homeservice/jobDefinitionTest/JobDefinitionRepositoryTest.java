package com.finalproject.homeservice.jobDefinitionTest;

import com.finalproject.homeservice.entity.JobDefinition;
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

    @Test
    public void getJobDefinitionByCategoryId(){
        long id = 2;
        List<JobDefinition> jobDefinitionList =
                jobDefinitionRepository.getJobDefinitionByCategoryId(id);
        jobDefinitionList.forEach(System.out::println);
    }
}
