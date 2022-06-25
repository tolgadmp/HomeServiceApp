package com.finalproject.homeservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Offer> offer = new ArrayList<>();

    @OneToMany(mappedBy = "job",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<JobAttributeChoice> jobAttributeChoices;


}
