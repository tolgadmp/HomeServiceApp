package com.finalproject.homeservice.payload.request;

import com.finalproject.homeservice.entity.Job;
import com.finalproject.homeservice.payload.response.ChoiceResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestDto {

    private String description;
    private List<ChoiceResponseDto> choiceList;

    public static JobRequestDto mapEntityToRequestDto(Job job){
        return JobRequestDto.builder()
                .description(job.getDescription())
                .choiceList(new ArrayList<>())
                .build();
    }

    /*public static Job mapRequestDtoToEntity(JobRequestDto requestDto){
        return Job.builder()
                .description(requestDto.getDescription())
                .build();
    }*/
}
