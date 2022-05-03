package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.request.AttributeRequestDto;
import com.finalproject.homeservice.payload.response.AttributeResponseDto;
import com.finalproject.homeservice.payload.response.JobDefinitionResponseDto;
import com.finalproject.homeservice.repository.AttributeRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final JobDefinitionService jobDefinitionService;

    public AttributeService(AttributeRepository attributeRepository
                            ,JobDefinitionService jobDefinitionService) {
        this.attributeRepository = attributeRepository;
        this.jobDefinitionService = jobDefinitionService;
    }

    public List<AttributeResponseDto> getAllAttributes(){
        List<Attribute> attributes = attributeRepository.findAll();

        List<AttributeResponseDto> responseDtos = attributes.stream()
                .map(attribute -> AttributeResponseDto.mapEntityToResponseDto(attribute))
                .collect(Collectors.toList());
        return responseDtos;
    }

    public AttributeResponseDto getAttributeById(long id){
        Attribute attribute = attributeRepository.getById(id);
        return AttributeResponseDto.mapEntityToResponseDto(attribute);
    }

    public List<AttributeResponseDto> getAttributeByJobDefinition(JobDefinition jobDefinition){
        List<Attribute> attributes = attributeRepository.getAttributesByJobDefinitions(jobDefinition);
        List<AttributeResponseDto> responseDtos = attributes.stream()
                .map(attribute -> AttributeResponseDto.mapEntityToResponseDto(attribute))
                .collect(Collectors.toList());
        return responseDtos;
    }

    public AttributeResponseDto addAttribute(long id, AttributeRequestDto attributeRequestDto){

        Attribute attribute = AttributeRequestDto.mapRequestDtoToEntity(attributeRequestDto);
        attributeRepository.save(attribute);

        JobDefinitionResponseDto jobDefinitionResponseDto = jobDefinitionService.getJobDefinitionById(id);
        JobDefinition jobDefinition = JobDefinitionResponseDto.mapResponseDtoToEntity(jobDefinitionResponseDto);
        List<Attribute> attributes = attributeRepository.getAttributesByJobDefinitions(jobDefinition);
        attributes.add(attribute);
        jobDefinition.setAttributes(attributes);

        jobDefinitionService.saveJobDefinition(jobDefinition);

        return AttributeResponseDto.mapEntityToResponseDto(attribute);
    }

    public AttributeResponseDto updateAttribute(AttributeRequestDto requestDto, long id){
        Attribute attribute = attributeRepository.getById(id);
        attribute.setProperty(requestDto.getProperty());
        attributeRepository.save(attribute);
        return AttributeResponseDto.mapEntityToResponseDto(attribute);
    }

    public void deleteAttribute(long id){
        Attribute attribute = attributeRepository.getById(id);
        attributeRepository.delete(attribute);
    }

    public void saveAttribute(Attribute attribute){
        attributeRepository.save(attribute);
    }

}
