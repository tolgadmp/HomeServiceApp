package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Attribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeRequestDto {

    private String property;

    public static AttributeRequestDto mapEntityToRequestDto(Attribute attribute){
        AttributeRequestDto attributeRequestDto = new AttributeRequestDto();
        attributeRequestDto.setProperty(attribute.getProperty());
        return attributeRequestDto;
    }

    public static Attribute mapRequestDtoToEntity(AttributeRequestDto attributeRequestDto){
        Attribute attribute = new Attribute();
        attribute.setProperty(attributeRequestDto.getProperty());
        return attribute;
    }
}
