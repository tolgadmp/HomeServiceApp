package com.finalproject.homeservice.payload;

import com.finalproject.homeservice.entity.JobDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDefinitionRequestDto {

    private Long id;

    private String name;

    private String description;


    public static JobDefinition mapDtoToEntity(JobDefinitionRequestDto jobDefinitionRequestDto){
        return JobDefinition.builder().id(jobDefinitionRequestDto.getId())
                .name(jobDefinitionRequestDto.getName())
                .description(jobDefinitionRequestDto.getDescription())
                .build();
    }
}
