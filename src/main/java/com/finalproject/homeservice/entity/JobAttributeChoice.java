package com.finalproject.homeservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "job_attribute_choice")
public class JobAttributeChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne()
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne()
    @JoinColumn(name = "choice_id")
    private Choice choice;


}
