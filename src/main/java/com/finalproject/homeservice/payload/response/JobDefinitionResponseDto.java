package com.finalproject.homeservice.payload.response;

import com.finalproject.homeservice.entity.JobDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDefinitionResponseDto {

    private Long id;

    private String name;

    private String description;

    private List<AttributeResponseDto> attributes;

    public static JobDefinitionResponseDto mapEntityToResponseDto(JobDefinition jobDefinition){

       return JobDefinitionResponseDto.builder().id(jobDefinition.getId())
                .name(jobDefinition.getName())
                .description(jobDefinition.getDescription())
                .attributes(new ArrayList<>())
                .build();
    }

    public static JobDefinition mapResponseDtoToEntity(JobDefinitionResponseDto jobDefinitionResponseDto){
        JobDefinition jobDefinition = new JobDefinition();
        jobDefinition.setId(jobDefinitionResponseDto.getId());
        jobDefinition.setName(jobDefinitionResponseDto.getName());
        jobDefinition.setDescription(jobDefinitionResponseDto.getDescription());
        return jobDefinition;
    }
}
