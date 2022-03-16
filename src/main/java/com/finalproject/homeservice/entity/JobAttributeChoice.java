package com.finalproject.homeservice.entity;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "job_attribute_choices")
public class JobAttributeChoice{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
