package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.JobDefinitionDto;
import com.finalproject.homeservice.service.JobDefinitionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobDefinition")
public class JobDefinitionController {

    private final JobDefinitionService jobDefinitionService;

    public JobDefinitionController(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }

    @GetMapping
    public List<JobDefinitionDto> getAllJobDefinitions(){
        return jobDefinitionService.getAllJobDefinitions();
    }

    @GetMapping("/{id}")
    public List<JobDefinitionDto> getJobDefinitionsByCategoryId(@PathVariable(name = "id")long id){
        return jobDefinitionService.getJobDefinitionByCategory(id);
    }


}
