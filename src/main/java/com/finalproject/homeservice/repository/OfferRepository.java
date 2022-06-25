package com.finalproject.homeservice.repository;

import com.finalproject.homeservice.entity.Job;
import com.finalproject.homeservice.entity.Offer;
import com.finalproject.homeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> getOfferByProfessional(User user);
    Offer getOfferByJob(Job job);
}
