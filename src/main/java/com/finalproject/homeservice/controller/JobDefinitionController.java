package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.*;
import com.finalproject.homeservice.service.AttributeService;
import com.finalproject.homeservice.service.JobDefinitionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-definition")
public class JobDefinitionController {

    private final JobDefinitionService jobDefinitionService;
    private final AttributeService attributeService;

    public JobDefinitionController(JobDefinitionService jobDefinitionService,
                                   AttributeService attributeService) {
        this.jobDefinitionService = jobDefinitionService;
        this.attributeService = attributeService;
    }


    @GetMapping("/{id}")
    public JobDefinitionDto getJobDefinitionWithAttributes(@PathVariable(name = "id")long id){
        return jobDefinitionService.getJobDefinitionWithAttributes(id);
    }

    @PostMapping("/{categoryId}")
    public JobDefinitionResponseDto addJobDefinition(@RequestBody JobDefinitionRequestDto jobDefinitionRequestDto,
                                                     @PathVariable(value = "categoryId")long id){
       return jobDefinitionService.addJobDefiniton(jobDefinitionRequestDto,id);
    }

    @PostMapping("/add-attribute/{id}")
    public AttributeDto addAttributeToJob(@RequestBody AttributeRequestDto attributeRequestDto,
                                          @PathVariable(value = "id")long id){
        return attributeService.addAttribute(id, attributeRequestDto);
    }


}
