package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.Category;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.AttributeDto;
import com.finalproject.homeservice.payload.JobDefinitionDto;
import com.finalproject.homeservice.payload.JobDefinitionRequestDto;
import com.finalproject.homeservice.payload.JobDefinitionResponseDto;
import com.finalproject.homeservice.repository.CategoryRepository;
import com.finalproject.homeservice.repository.JobDefinitionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobDefinitionService {

    private final JobDefinitionRepository jobDefinitionRepository;
    private final AttributeService attributeService;
    private final ChoiceService choiceService;
    private final CategoryRepository categoryRepository;

    public JobDefinitionService(JobDefinitionRepository jobDefinitionRepository,
                                @Lazy AttributeService attributeService,
                                ChoiceService choiceService,
                                CategoryRepository categoryRepository) {
        this.jobDefinitionRepository = jobDefinitionRepository;
        this.attributeService = attributeService;
        this.choiceService = choiceService;
        this.categoryRepository = categoryRepository;
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

    public JobDefinitionResponseDto getJobDefinitionById(long id){
        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        JobDefinitionResponseDto jobDefinitionResponseDto = JobDefinitionResponseDto.mapEntityToDto(jobDefinition);
        return jobDefinitionResponseDto;
    }

    public JobDefinitionDto getJobDefinitionWithAttributes(long id){

        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        List<AttributeDto> attributes = attributeService.getAttributeByJobDefinition(jobDefinition);
        attributes
                .forEach(attributeDto -> attributeDto.setChoiceDtos(choiceService.getChoicesByAttributes(attributeDto)));
        JobDefinitionDto jobDefinitionDto = mapToDto(jobDefinition);
        jobDefinitionDto.setAttributes(attributes);

        return jobDefinitionDto;
    }

    public JobDefinitionResponseDto addJobDefiniton(JobDefinitionRequestDto jobDefinitionRequestDto, long id){
        Category category = categoryRepository.getById(id);
       JobDefinition jobDefinition = JobDefinitionRequestDto.mapDtoToEntity(jobDefinitionRequestDto);
       jobDefinition.setCategory(category);
       JobDefinition newJobDefiniton = jobDefinitionRepository.save(jobDefinition);
       return JobDefinitionResponseDto.mapEntityToDto(newJobDefiniton);
    }

    public void updateJobDefinition(JobDefinition jobDefinition){
        jobDefinitionRepository.save(jobDefinition);
    }



    private JobDefinitionDto mapToDto(JobDefinition jobDefinition){

        JobDefinitionDto jobDefinitionDto = new JobDefinitionDto();
        jobDefinitionDto.setId(jobDefinition.getId());
        jobDefinitionDto.setName(jobDefinition.getName());
        jobDefinitionDto.setDescription(jobDefinition.getDescription());

        return jobDefinitionDto;
    }

}
