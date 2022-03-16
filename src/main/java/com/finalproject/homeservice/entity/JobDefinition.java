package com.finalproject.homeservice.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "job_definitions")
public class JobDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "jobDefinition")
    private Set<Job> jobs;

    @ManyToMany
    @JoinTable(name = "job_definition_attributes",
            joinColumns = @JoinColumn(name = "job_definition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"))
    private Set<Attribute> attributes;

    @OneToOne(mappedBy = "jobDefinition")
    private Expertise expertise;
}
