package com.finalproject.homeservice.repository;


import com.finalproject.homeservice.entity.Job;
import com.finalproject.homeservice.entity.Offer;
import com.finalproject.homeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> getJobByCustomer(User user);

    Job getJobByOffer(Offer offer);


}
