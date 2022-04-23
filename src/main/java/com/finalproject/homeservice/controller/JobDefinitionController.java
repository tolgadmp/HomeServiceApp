package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.JobDefinitionDto;
import com.finalproject.homeservice.service.JobDefinitionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-definition")
public class JobDefinitionController {

    private final JobDefinitionService jobDefinitionService;

    public JobDefinitionController(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }


    @GetMapping("/{id}")
    public JobDefinitionDto getJobDefinitionWithAttributes(@PathVariable(name = "id")long id){
        return jobDefinitionService.getJobDefinitionWithAttributes(id);
    }


}
