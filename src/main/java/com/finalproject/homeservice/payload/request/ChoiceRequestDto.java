package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChoiceRequestDto {

    private String selection;

    public static ChoiceRequestDto mapEntityToRequestDto(Choice choice){
        return ChoiceRequestDto.builder()
                .selection(choice.getSeleciton())
                .build();
    }

    public static Choice mapRequestDtoToEntity(ChoiceRequestDto requestDto){
        return Choice.builder()
                .seleciton(requestDto.getSelection())
                .build();
    }
}
