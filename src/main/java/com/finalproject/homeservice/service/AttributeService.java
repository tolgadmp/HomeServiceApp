package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.AttributeDto;
import com.finalproject.homeservice.payload.AttributeRequestDto;
import com.finalproject.homeservice.payload.JobDefinitionResponseDto;
import com.finalproject.homeservice.repository.AttributeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final JobDefinitionService jobDefinitionService;

    public AttributeService(AttributeRepository attributeRepository, JobDefinitionService jobDefinitionService) {
        this.attributeRepository = attributeRepository;
        this.jobDefinitionService = jobDefinitionService;
    }

    public List<AttributeDto> getAttributeByJobDefinition(JobDefinition jobDefinition){
        List<Attribute> attributes = attributeRepository.getAttributesByJobDefinitions(jobDefinition);
        List<AttributeDto> attributeDtos = attributes.stream()
                .map(attribute -> AttributeDto.mapEntityToDto(attribute))
                .collect(Collectors.toList());
        return attributeDtos;
    }

    public AttributeDto addAttribute(long id, AttributeRequestDto attributeRequestDto){

        Attribute attribute = AttributeRequestDto.mapDtoToEntity(attributeRequestDto);
        attributeRepository.save(attribute);

        JobDefinitionResponseDto jobDefinitionResponseDto = jobDefinitionService.getJobDefinitionById(id);
        JobDefinition jobDefinition = JobDefinitionResponseDto.mapDtoToEntity(jobDefinitionResponseDto);
        List<Attribute> attributes = attributeRepository.getAttributesByJobDefinitions(jobDefinition);
        attributes.add(attribute);
        jobDefinition.setAttributes(attributes);

        jobDefinitionService.updateJobDefinition(jobDefinition);
        AttributeDto attributeDto = AttributeDto.mapEntityToDto(attribute);
        return attributeDto;

    }

}
