package com.finalproject.homeservice.payload;

import com.finalproject.homeservice.entity.JobDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDefinitionResponseDto {

    private Long id;

    private String name;

    private String description;

    public static JobDefinitionResponseDto mapEntityToDto(JobDefinition jobDefinition){
        JobDefinitionResponseDto jobDefinitionResponseDto = new JobDefinitionResponseDto();
        jobDefinitionResponseDto.setId(jobDefinition.getId());
        jobDefinitionResponseDto.setName(jobDefinition.getName());
        jobDefinitionResponseDto.setDescription(jobDefinition.getDescription());
        return jobDefinitionResponseDto;
    }

    public static JobDefinition mapDtoToEntity(JobDefinitionResponseDto jobDefinitionResponseDto){
        JobDefinition jobDefinition = new JobDefinition();
        jobDefinition.setId(jobDefinitionResponseDto.getId());
        jobDefinition.setName(jobDefinitionResponseDto.getName());
        jobDefinition.setDescription(jobDefinitionResponseDto.getDescription());
        return jobDefinition;
    }
}
