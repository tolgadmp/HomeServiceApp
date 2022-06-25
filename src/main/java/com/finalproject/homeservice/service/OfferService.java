package com.finalproject.homeservice.service;

import com.finalproject.homeservice.entity.Job;
import com.finalproject.homeservice.entity.Offer;
import com.finalproject.homeservice.entity.User;
import com.finalproject.homeservice.payload.request.OfferRequestDto;
import com.finalproject.homeservice.payload.response.OfferResponseDto;
import com.finalproject.homeservice.repository.JobRepository;
import com.finalproject.homeservice.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final JobRepository jobRepository;
    private final UserService userService;

    public OfferService(OfferRepository offerRepository,
                        JobRepository jobRepository,
                        UserService userService) {
        this.offerRepository = offerRepository;
        this.jobRepository = jobRepository;
        this.userService = userService;
    }

    public void createOffer(long id, OfferRequestDto offerRequestDto, String email){
        Job job = jobRepository.getById(id);
        User user = userService.getUserByEmail(email);
        Offer offer = new Offer();
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (86400 * 7 * 1000));
        offer.setAccepted(false);
        offer.setPrice(offerRequestDto.getPrice());
        offer.setStartDate(startDate);
        offer.setEndDate(endDate);
        offer.setJob(job);
        offer.setProfessional(user);
        offerRepository.save(offer);
    }

    public void updateOffer(long id, OfferRequestDto offerRequestDto){
        Offer offer = offerRepository.getById(id);
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (86400 * 7 * 1000));
        offer.setPrice(offerRequestDto.getPrice());
        offer.setStartDate(startDate);
        offer.setEndDate(endDate);
        offerRepository.save(offer);
    }

    public List<OfferResponseDto> getOfferByUser(String email){
        User user = userService.getUserByEmail(email);
        List<Offer> offers = offerRepository.getOfferByProfessional(user);
        List<Job> jobs = offers
                .stream()
                .map(offer -> jobRepository.getJobByOffer(offer))
                .collect(Collectors.toList());
        for(int i = 0; i < offers.size(); i++){
            offers.get(i).setJob(jobs.get(i));
        }
        return offers
                .stream()
                .map(offer -> OfferResponseDto.mapEntityToResponseDto(offer))
                .collect(Collectors.toList());
    }

    public void deleteOffer(long id){
        Offer offer = offerRepository.getById(id);
        offerRepository.delete(offer);
    }

    public void acceptOffer(long id,boolean accepted){
        Job job = jobRepository.getById(id);
        Offer offer = offerRepository.getOfferByJob(job);
        offer.setAccepted(accepted);
        if(job.isStatus() && offer.isAccepted()){
            job.setStatus(false);
        }
        else if(job.isStatus() && !offer.isAccepted()){
            job.setStatus(true);
        }
        jobRepository.save(job);
        offerRepository.save(offer);
    }
}
