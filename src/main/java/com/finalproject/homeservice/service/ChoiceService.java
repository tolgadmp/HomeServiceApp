package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.Choice;
import com.finalproject.homeservice.payload.request.ChoiceRequestDto;
import com.finalproject.homeservice.payload.response.AttributeResponseDto;
import com.finalproject.homeservice.payload.response.ChoiceResponseDto;
import com.finalproject.homeservice.repository.ChoiceRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final AttributeService attributeService;

    public ChoiceService(ChoiceRepository choiceRepository,
                         @Lazy AttributeService attributeService) {
        this.choiceRepository = choiceRepository;
        this.attributeService = attributeService;
    }

    public List<ChoiceResponseDto> getChoicesByAttributes(AttributeResponseDto responseDto){
        Attribute attribute = AttributeResponseDto.mapResponseDtoToEntity(responseDto);
        List<Choice> choices = choiceRepository.getChoicesByAttributes(attribute);
        return choices.stream()
                .map(choice -> ChoiceResponseDto.mapEntityToResponseDto(choice))
                .collect(Collectors.toList());
    }
    public List<Choice> getChoicesByAttribute(Attribute attribute){
       return choiceRepository.getChoicesByAttributes(attribute);
    }


    public ChoiceResponseDto createChoice(long id, ChoiceRequestDto requestDto){
        Choice choice = ChoiceRequestDto.mapRequestDtoToEntity(requestDto);
        choiceRepository.save(choice);
        AttributeResponseDto attributeResponseDto = attributeService.getAttributeById(id);
        Attribute attribute = AttributeResponseDto.mapResponseDtoToEntity(attributeResponseDto);
        List<ChoiceResponseDto> responseDtos = getChoicesByAttributes(attributeResponseDto);
        List<Choice> choices = responseDtos.stream()
                .map(responseDto -> ChoiceResponseDto.mapResponseDtoToEntity(responseDto))
                .collect(Collectors.toList());
        choices.add(choice);
        attribute.setChoices(choices);
        attributeService.saveAttribute(attribute);
        return ChoiceResponseDto.mapEntityToResponseDto(choice);
    }

    public ChoiceResponseDto updateChoice(long id, ChoiceRequestDto requestDto){
        Choice choice = choiceRepository.getById(id);
        choice.setSeleciton(requestDto.getSelection());
        choiceRepository.save(choice);
        return ChoiceResponseDto.mapEntityToResponseDto(choice);
    }

    public void deleteChoice(long id){
        Choice choice = choiceRepository.getById(id);
        List<Attribute> attributes = attributeService.getAttributesByChoice(choice);
        attributes.clear();
        choice.setAttributes(attributes);
        choiceRepository.save(choice);
        choiceRepository.delete(choice);
    }


}
