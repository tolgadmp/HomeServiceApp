package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.Category;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.request.JobDefinitionRequestDto;
import com.finalproject.homeservice.payload.response.AttributeResponseDto;
import com.finalproject.homeservice.payload.response.JobDefinitionResponseDto;
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

    public List<JobDefinitionResponseDto> getAllJobDefinitions(){
        List<JobDefinition> jobList =  jobDefinitionRepository.findAll();
        List<JobDefinitionResponseDto> responseDtos = jobList.stream()
                .map(jobDefinition -> JobDefinitionResponseDto.mapEntityToResponseDto(jobDefinition))
                .collect(Collectors.toList());
        return responseDtos;
    }

    public List<JobDefinitionResponseDto> getJobDefinitionByCategory(long id){
        List<JobDefinition> jobDefinitions = jobDefinitionRepository.getJobDefinitionByCategoryId(id);
        List<JobDefinitionResponseDto> responseDtos = jobDefinitions
                .stream()
                .map(jobDefinition -> JobDefinitionResponseDto.mapEntityToResponseDto(jobDefinition))
                .collect(Collectors.toList());
        return responseDtos;
    }

    public JobDefinitionResponseDto getJobDefinitionById(long id){
        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        JobDefinitionResponseDto jobDefinitionResponseDto = JobDefinitionResponseDto.mapEntityToResponseDto(jobDefinition);
        return jobDefinitionResponseDto;
    }

    public JobDefinitionResponseDto getJobDefinitionWithAttributes(long id){

        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        List<AttributeResponseDto> attributes = attributeService.getAttributeByJobDefinition(jobDefinition);
        attributes
                .forEach(responseDto -> responseDto.setChoiceResponseDtos(choiceService.getChoicesByAttributes(responseDto)));
        JobDefinitionResponseDto responseDto = JobDefinitionResponseDto.mapEntityToResponseDto(jobDefinition);
        responseDto.setAttributes(attributes);
        return responseDto;
    }

    public JobDefinitionResponseDto createJobDefiniton(JobDefinitionRequestDto jobDefinitionRequestDto, long id){
        Category category = categoryRepository.getById(id);
       JobDefinition jobDefinition = JobDefinitionRequestDto.mapRequestDtoToEntity(jobDefinitionRequestDto);
       jobDefinition.setCategory(category);
       JobDefinition newJobDefiniton = jobDefinitionRepository.save(jobDefinition);
       return JobDefinitionResponseDto.mapEntityToResponseDto(newJobDefiniton);
    }

    public void saveJobDefinition(JobDefinition jobDefinition){
        jobDefinitionRepository.save(jobDefinition);
    }

    public JobDefinitionResponseDto updateJobDefinition(long id, JobDefinitionRequestDto requestDto){

        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        jobDefinition.setId(requestDto.getId());
        jobDefinition.setName(requestDto.getName());
        jobDefinition.setDescription(requestDto.getDescription());
        jobDefinitionRepository.save(jobDefinition);

        return  JobDefinitionResponseDto.mapEntityToResponseDto(jobDefinition);
    }

    public void deleteAttributeFromJobDefiniton(long jobDefinitionId, long attributeId){
        JobDefinition jobDefinition = jobDefinitionRepository.getById(jobDefinitionId);
        List<AttributeResponseDto> attributes =  attributeService.getAttributeByJobDefinition(jobDefinition);
        AttributeResponseDto removedAttribute = attributeService.getAttributeById(attributeId);
        attributes.remove(removedAttribute);
        List<Attribute> updatedAttributes = attributes.stream()
                .map(attribute -> AttributeResponseDto.mapResponseDtoToEntity(attribute))
                .collect(Collectors.toList());
        jobDefinition.setAttributes(updatedAttributes);
        jobDefinitionRepository.save(jobDefinition);
    }


    public void deleteJobDefinition(long id){
        JobDefinition jobDefinition = jobDefinitionRepository.getById(id);
        jobDefinitionRepository.delete(jobDefinition);
    }

    public List<JobDefinition> getJobDefinitonsByAttribute(Attribute attribute){
        return jobDefinitionRepository.getJobDefinitionsByAttributes(attribute);
    }

}
