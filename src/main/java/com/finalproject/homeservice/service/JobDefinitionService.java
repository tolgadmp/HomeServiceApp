package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.JobDefinitionDto;
import com.finalproject.homeservice.repository.AttributeRepository;
import com.finalproject.homeservice.repository.JobDefinitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobDefinitionService {

    private final JobDefinitionRepository jobDefinitionRepository;

    public JobDefinitionService(JobDefinitionRepository jobDefinitionRepository) {
        this.jobDefinitionRepository = jobDefinitionRepository;

    }

    public List<JobDefinitionDto> getAllJobDefinitions(){
        List<JobDefinition> jobList =  jobDefinitionRepository.findAll();
        List<JobDefinitionDto> jobs = jobList.stream()
                .map(jobDefinition -> mapToDto(jobDefinition))
                .collect(Collectors.toList());
        return jobs;
    }

    public List<JobDefinitionDto> getJobDefinitionByCategory(long id){
        List<JobDefinition> jobDefinitions = jobDefinitionRepository.getJobDefinitionByCategoryId(id);
        List<JobDefinitionDto> jobList = jobDefinitions
                .stream()
                .map(jobDefinition -> mapToDto(jobDefinition))
                .collect(Collectors.toList());
        return jobList;
    }


    private JobDefinitionDto mapToDto(JobDefinition jobDefinition){

        JobDefinitionDto jobDefinitionDto = new JobDefinitionDto();
        jobDefinitionDto.setId(jobDefinition.getId());
        jobDefinitionDto.setName(jobDefinition.getName());
        jobDefinitionDto.setDescription(jobDefinition.getDescription());

        return jobDefinitionDto;
    }

}
