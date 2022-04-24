package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.Choice;
import com.finalproject.homeservice.payload.AttributeDto;
import com.finalproject.homeservice.payload.ChoiceDto;
import com.finalproject.homeservice.repository.ChoiceRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    public List<ChoiceDto> getChoicesByAttributes(AttributeDto attributeDto){
        Attribute attribute = AttributeDto.mapDtoToEntity(attributeDto);
        List<Choice> choices = choiceRepository.getChoicesByAttributes(attribute);
        return choices.stream()
                .map(choice -> ChoiceDto.mapEntityToDto(choice))
                .collect(Collectors.toList());
    }
}
