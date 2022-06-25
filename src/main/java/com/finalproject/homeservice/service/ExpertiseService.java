package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Expertise;
import com.finalproject.homeservice.entity.JobDefinition;
import com.finalproject.homeservice.payload.ExpertiseDto;
import com.finalproject.homeservice.repository.ExpertiseRepository;
import com.finalproject.homeservice.repository.JobDefinitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpertiseService {

    private final ExpertiseRepository expertiseRepository;
    private final JobDefinitionRepository jobDefinitionRepository;

    public ExpertiseService(ExpertiseRepository expertiseRepository,
                            JobDefinitionRepository jobDefinitionRepository) {
        this.expertiseRepository = expertiseRepository;
        this.jobDefinitionRepository = jobDefinitionRepository;
    }

    public ExpertiseDto craeateExpertise(ExpertiseDto expertiseDto){
        Expertise expertise = ExpertiseDto.mapDtoToEntity(expertiseDto);
        JobDefinition jobDefinition =
                jobDefinitionRepository.getJobDefinitionsByName(expertiseDto.getExpertiseName());
        expertise.setJobDefinition(jobDefinition);
        expertiseRepository.save(expertise);
        return ExpertiseDto.mapEntityToDto(expertise);
    }

    public List<ExpertiseDto> getAllExpertises(){
        List<Expertise> expertises = expertiseRepository.findAll();
        return expertises
                .stream()
                .map(expertise -> ExpertiseDto.mapEntityToDto(expertise))
                .collect(Collectors.toList());
    }

    public ExpertiseDto updateExpertise(long id,ExpertiseDto expertiseDto){
        Expertise expertise = expertiseRepository.getById(id);
        expertise.setExpertiseName(expertiseDto.getExpertiseName());
        expertise.setDescription(expertiseDto.getDescription());
        expertiseRepository.save(expertise);
        return ExpertiseDto.mapEntityToDto(expertise);
    }

    public void deleteExpertise(long id){
        Expertise expertise = expertiseRepository.getById(id);
        expertiseRepository.delete(expertise);
    }
}
