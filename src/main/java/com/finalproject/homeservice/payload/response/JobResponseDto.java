package com.finalproject.homeservice.payload.response;

import com.finalproject.homeservice.entity.Job;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDto {

    private String description;
    private UserResponseDto userResponseDto;
    private List<ChoiceResponseDto> choiseList;
    private List<AttributeResponseDto> attributeList;

    public static JobResponseDto mapEntityToResponseDto(Job job){
        return JobResponseDto.builder()
                .description(job.getDescription())
                .userResponseDto(new UserResponseDto())
                .choiseList(new ArrayList<>())
                .attributeList(new ArrayList<>())
                .build();
    }

}
