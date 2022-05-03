package com.finalproject.homeservice.payload.response;

import com.finalproject.homeservice.entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChoiceResponseDto {

    private Long id;

    private String selection;

    public static ChoiceResponseDto mapEntityToResponseDto(Choice choice){
        return ChoiceResponseDto.builder()
                .id(choice.getId())
                .selection(choice.getSeleciton())
                .build();
    }

    public static Choice mapResponseDtoToEntity(ChoiceResponseDto responseDto){
        return Choice.builder()
                .id(responseDto.getId())
                .seleciton(responseDto.getSelection())
                .build();
    }
}
