package com.finalproject.homeservice.payload.response;

import com.finalproject.homeservice.entity.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeResponseDto {

    private Long id;

    private String property;

    private List<ChoiceResponseDto> choiceResponseDtos;

    public static AttributeResponseDto mapEntityToResponseDto(Attribute attribute){
        return AttributeResponseDto.builder().id(attribute.getId())
                .property(attribute.getProperty())
                .build();
    }

    public static Attribute mapResponseDtoToEntity(AttributeResponseDto responseDto){
        return Attribute.builder()
                .id(responseDto.getId())
                .property(responseDto.getProperty())
                .build();
    }

}
