package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.JobDefinitionDto;
import com.finalproject.homeservice.service.JobDefinitionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final JobDefinitionService jobDefinitionService;

    public HomeController(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }

    @GetMapping("/{id}")
    public List<JobDefinitionDto> getJobDefinitionsByCategoryId(@PathVariable(name = "id")long id){
        return jobDefinitionService.getJobDefinitionByCategory(id);
    }
}
