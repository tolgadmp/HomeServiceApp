package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.AttributeDto;
import com.finalproject.homeservice.payload.JobDefinitionDto;
import com.finalproject.homeservice.repository.AttributeRepository;
import com.finalproject.homeservice.repository.ChoiceRepository;
import com.finalproject.homeservice.repository.JobDefinitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobDefinitionService {

    private final JobDefinitionRepository jobDefinitionRepository;
    private final AttributeRepository attributeRepository;
    private final ChoiceRepository choiceRepository;

    public JobDefinitionService(JobDefinitionRepository jobDefinitionRepository,
                                AttributeRepository attributeRepository,
                                ChoiceRepository choiceRepository) {
        this.jobDefinitionRepository = jobDefinitionRepository;
        this.attributeRepository = attributeRepository;
        this.choiceRepository = choiceRepository;
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

    public JobDefinitionDto getJobDefinitionWithAttributes(long id){
        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        List<Attribute> attributes = attributeRepository.getAttributesByJobDefinitions(jobDefinition);
        attributes.stream().
                forEach(attribute -> attribute.setChoices(choiceRepository.getChoicesByAttributes(attribute)));
        List<AttributeDto> jobDefinitionAttributes = AttributeDto.mapEntityListToDtoList(attributes);
        JobDefinitionDto jobDefinitionDto = mapToDto(jobDefinition);
        jobDefinitionDto.setAttributes(jobDefinitionAttributes);
        return jobDefinitionDto;
    }



    private JobDefinitionDto mapToDto(JobDefinition jobDefinition){

        JobDefinitionDto jobDefinitionDto = new JobDefinitionDto();
        jobDefinitionDto.setId(jobDefinition.getId());
        jobDefinitionDto.setName(jobDefinition.getName());
        jobDefinitionDto.setDescription(jobDefinition.getDescription());

        return jobDefinitionDto;
    }

}
