package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.JobDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDefinitionRequestDto {

    private Long id;

    private String name;

    private String description;


    public static JobDefinition mapRequestDtoToEntity(JobDefinitionRequestDto jobDefinitionRequestDto){
        return JobDefinition.builder().id(jobDefinitionRequestDto.getId())
                .name(jobDefinitionRequestDto.getName())
                .description(jobDefinitionRequestDto.getDescription())
                .build();
    }

    public static JobDefinitionRequestDto mapEntityToRequestDto(JobDefinition jobDefinition){
        return JobDefinitionRequestDto.builder().id(jobDefinition.getId())
                .name(jobDefinition.getName())
                .description(jobDefinition.getDescription())
                .build();
    }
}
