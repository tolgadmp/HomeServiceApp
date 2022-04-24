package com.finalproject.homeservice.payload;

import com.finalproject.homeservice.entity.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDto {

    private Long id;

    private String property;

    private List<ChoiceDto> choiceDtos;


    public static AttributeDto mapEntityToDto(Attribute attribute){
        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setId(attribute.getId());
        attributeDto.setProperty(attribute.getProperty());
        return attributeDto;
    }

    public static Attribute mapDtoToEntity(AttributeDto attributeDto){
        Attribute attribute = new Attribute();
        attribute.setId(attributeDto.getId());
        attribute.setProperty(attributeDto.getProperty());
        return attribute;
    }

    public static List<AttributeDto> mapEntityListToDtoList(List<Attribute> attributes){
        return attributes.stream()
                .map(attribute -> new AttributeDto(attribute.getId(),
                        attribute.getProperty(),
                        ChoiceDto.mapEntityListToDtoList(attribute.getChoices())))
                .collect(Collectors.toList());
    }


}
