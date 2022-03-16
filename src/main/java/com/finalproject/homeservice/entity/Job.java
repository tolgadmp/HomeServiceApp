package com.finalproject.homeservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "job_definition_id")
    private JobDefinition jobDefinition;

    @OneToMany(mappedBy = "job" , cascade = CascadeType.ALL)
    private Set<Offer> offer = new HashSet<>();




}
