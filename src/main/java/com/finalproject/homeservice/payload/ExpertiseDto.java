package com.finalproject.homeservice.payload;


import com.finalproject.homeservice.entity.Expertise;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertiseDto {

    private String expertiseName;
    private String description;

    public static Expertise mapDtoToEntity(ExpertiseDto requestDto){
        return Expertise.builder()
                .expertiseName(requestDto.getExpertiseName())
                .description(requestDto.getDescription())
                .build();
    }

    public static ExpertiseDto mapEntityToDto(Expertise expertise){
        return ExpertiseDto.builder()
                .expertiseName(expertise.getExpertiseName())
                .description(expertise.getDescription())
                .build();
    }

}
