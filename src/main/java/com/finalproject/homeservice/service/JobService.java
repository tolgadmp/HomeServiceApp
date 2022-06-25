package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.*;
import com.finalproject.homeservice.payload.request.JobRequestDto;
import com.finalproject.homeservice.payload.response.*;
import com.finalproject.homeservice.repository.JobAttributeChoiceRepository;
import com.finalproject.homeservice.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobAttributeChoiceRepository jobAttributeChoiceRepository;
    private final JobDefinitionService jobDefinitionService;
    private final AttributeService attributeService;
    private final UserService userService;

    public JobService(JobRepository jobRepository,
                      JobAttributeChoiceRepository jobAttributeChoiceRepository,
                      JobDefinitionService jobDefinitionService,
                      AttributeService attributeService,
                      UserService userService){
        this.jobRepository = jobRepository;
        this.jobAttributeChoiceRepository = jobAttributeChoiceRepository;
        this.jobDefinitionService = jobDefinitionService;
        this.attributeService = attributeService;
        this.userService = userService;
    }

    public void createJob(long jobDefinitionId, JobRequestDto jobRequestDto, String email){
        Job job = new Job();
        User user = userService.getUserByEmail(email);
        JobDefinition jobDefinition = JobDefinitionResponseDto
                .mapResponseDtoToEntity(jobDefinitionService.getJobDefinitionById(jobDefinitionId));
        job.setJobDefinition(jobDefinition);
        job.setCustomer(user);
        job.setDescription(jobRequestDto.getDescription());
        job.setStatus(true);
        jobRepository.save(job);
        List<Attribute> attributes = attributeService.getAttributesByJobDefinition(jobDefinition);
        List<Choice> choices = jobRequestDto.getChoiceList()
                .stream()
                .map(choiceResponseDto -> ChoiceResponseDto.mapResponseDtoToEntity(choiceResponseDto))
                .collect(Collectors.toList());
        for (int i = 0; i < attributes.size(); i++){
            JobAttributeChoice jobAttributeChoice = new JobAttributeChoice();
            jobAttributeChoice.setAttribute(attributes.get(i));
            jobAttributeChoice.setChoice(choices.get(i));
            jobAttributeChoice.setJob(job);
            jobAttributeChoiceRepository.save(jobAttributeChoice);
        }
    }

    public JobResponseDto getJobWithAttributeAndChoice(long id,String email){
        Job job = jobRepository.getById(id);
        List<JobAttributeChoice> jobAttributeChoices = jobAttributeChoiceRepository.getJobAttributeChoiceByJob(job);
        UserResponseDto user = userService.getUser(email);
        JobResponseDto jobResponseDto = JobResponseDto.mapEntityToResponseDto(job);
        List<Attribute> attributeList = new ArrayList<>();
        List<Choice> choiceList = new ArrayList<>();
        for (JobAttributeChoice jobAttributeChoice : jobAttributeChoices) {
            attributeList.add(jobAttributeChoice.getAttribute());
            choiceList.add(jobAttributeChoice.getChoice());
        }
        List<AttributeResponseDto> attributes = attributeList
                .stream()
                .map(attribute -> AttributeResponseDto.mapEntityToResponseDto(attribute))
                        .collect(Collectors.toList());
        List<ChoiceResponseDto> choices = choiceList
                .stream()
                .map(choice -> ChoiceResponseDto.mapEntityToResponseDto(choice))
                        .collect(Collectors.toList());

        jobResponseDto.setAttributeList(attributes);
        jobResponseDto.setChoiseList(choices);
        jobResponseDto.setUserResponseDto(user);
        return jobResponseDto;
    }

    public List<JobResponseDto> getJobByUser(String email){
        User user = userService.getUserByEmail(email);
        UserResponseDto userResponseDto = UserResponseDto.mapEntityToResponseDto(user);
        List<Job> jobs = jobRepository.getJobByCustomer(user);
        List<JobResponseDto> jobList = jobs
               .stream()
               .map(job -> JobResponseDto.mapEntityToResponseDto(job))
               .collect(Collectors.toList());
        jobList.forEach(jobResponseDto -> jobResponseDto.setUserResponseDto(userResponseDto));
        return jobList;
    }

    public JobResponseDto updateJob(long id, JobRequestDto jobRequestDto){
        Job job = jobRepository.getById(id);
        List<JobAttributeChoice> jobAttributeChoices = jobAttributeChoiceRepository.getJobAttributeChoiceByJob(job);
        job.setDescription(jobRequestDto.getDescription());
        List<ChoiceResponseDto> choices = jobRequestDto.getChoiceList();
        List<Choice> newChoices = choices.
                stream()
                .map(responseDto -> ChoiceResponseDto.mapResponseDtoToEntity(responseDto))
                .collect(Collectors.toList());
        for(int i = 0; i < jobAttributeChoices.size(); i++){
            jobAttributeChoices.get(i).setChoice(newChoices.get(i));
        }
        List<AttributeResponseDto> attributes = jobAttributeChoices
                .stream()
                .map(jobAttributeChoice -> AttributeResponseDto.mapEntityToResponseDto(jobAttributeChoice.getAttribute()))
                        .collect(Collectors.toList());
        jobRepository.save(job);
        jobAttributeChoiceRepository.saveAll(jobAttributeChoices);
        JobResponseDto jobResponseDto = JobResponseDto.mapEntityToResponseDto(job);
        jobResponseDto.setChoiseList(choices);
        jobResponseDto.setAttributeList(attributes);
        return jobResponseDto;
    }

    public void deleteJob(long id){
        Job job = jobRepository.getById(id);
        List<JobAttributeChoice> jobAttributeChoices = jobAttributeChoiceRepository.getJobAttributeChoiceByJob(job);
        jobAttributeChoiceRepository.deleteAll(jobAttributeChoices);
        jobRepository.delete(job);
    }
}
