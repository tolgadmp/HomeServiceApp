package com.finalproject.homeservice.controller;

import com.finalproject.homeservice.payload.request.JobDefinitionRequestDto;
import com.finalproject.homeservice.payload.response.JobDefinitionResponseDto;
import com.finalproject.homeservice.service.JobDefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-definition")
public class JobDefinitionController {

    private final JobDefinitionService jobDefinitionService;

    public JobDefinitionController(JobDefinitionService jobDefinitionService) {
        this.jobDefinitionService = jobDefinitionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDefinitionResponseDto> getJobDefinitionWithAttributes(@PathVariable(name = "id")long id){
        return new ResponseEntity<>(jobDefinitionService.getJobDefinitionWithAttributes(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/{categoryId}")
    public ResponseEntity<JobDefinitionResponseDto> createJobDefinition(@RequestBody JobDefinitionRequestDto jobDefinitionRequestDto,
                                                     @PathVariable(value = "categoryId")long id){
       return new ResponseEntity<>(jobDefinitionService.createJobDefiniton(jobDefinitionRequestDto,id), HttpStatus.OK) ;
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<JobDefinitionResponseDto> updateJobDefinition(@RequestBody JobDefinitionRequestDto requestDto,
                                                                        @PathVariable(name = "id")long id){
        return new ResponseEntity<>(jobDefinitionService.updateJobDefinition(id, requestDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobDefinition(@PathVariable(name = "id")long id){
        jobDefinitionService.deleteJobDefinition(id);
        return new ResponseEntity<>("JobDefinition başarıyla silindi." ,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteAttribute/{jobDefinitionId}/{attributeId}")
    public ResponseEntity<String> deleteAttributeFromJobDefinition(@PathVariable(name = "jobDefinitionId")long id,
                                                                   @PathVariable(name = "attributeId") long attributeId){
        jobDefinitionService.deleteAttributeFromJobDefiniton(id, attributeId);
        return new ResponseEntity<>("Özellik başarıyla silindi." , HttpStatus.OK);
    }




}
