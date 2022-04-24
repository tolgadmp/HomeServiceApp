package com.finalproject.homeservice.payload;

import com.finalproject.homeservice.entity.Attribute;
import com.finalproject.homeservice.entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceDto {

    private Long id;

    private String selection;

    public static ChoiceDto mapEntityToDto(Choice choice){
        ChoiceDto choiceDto = new ChoiceDto();
        choiceDto.setId(choice.getId());
        choiceDto.setSelection(choice.getSeleciton());
        return choiceDto;
    }

    public static List<ChoiceDto> mapEntityListToDtoList(List<Choice> choices){
        return choices.stream().map(choice -> new ChoiceDto(choice.getId(), choice.getSeleciton()))
                .collect(Collectors.toList());
    }
}
