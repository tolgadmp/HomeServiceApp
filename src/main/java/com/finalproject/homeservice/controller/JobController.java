package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.request.JobRequestDto;
import com.finalproject.homeservice.payload.response.JobResponseDto;
import com.finalproject.homeservice.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/{jobDefinitionId}/createJob")
    public ResponseEntity<String> createJob(@PathVariable(name = "jobDefinitionId") long jobDefinitionId,
                                            @RequestBody JobRequestDto requestDto){

        String email = getUserByContext();
        jobService.createJob(jobDefinitionId,requestDto,email);

        return new ResponseEntity<>("İş başarıyla oluşturuldu", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJobWithAttributeAndChoice(@PathVariable(name = "id")long id){

        String email = getUserByContext();
        return new ResponseEntity<>(jobService.getJobWithAttributeAndChoice(id,email), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDto>> getJobsByUser(){
        String email = getUserByContext();
        System.out.println(email);
        return new ResponseEntity<>(jobService.getJobByUser(email),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDto> updateJob(@PathVariable(name = "id")long id,
                                                    @RequestBody  JobRequestDto requestDto){
        return new ResponseEntity<>(jobService.updateJob(id,requestDto), HttpStatus.OK);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable(name = "id")long id){
        jobService.deleteJob(id);
        return new ResponseEntity<>("Silme işlemi başarılı",HttpStatus.OK);
    }

    private String getUserByContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
