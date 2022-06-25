package com.finalproject.homeservice.JobTest;

import com.finalproject.homeservice.entity.Job;
import com.finalproject.homeservice.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void getJobById(){
        long id = 50;
        Job job = jobRepository.getById(id);
        System.out.println(job.isStatus());
    }
}
