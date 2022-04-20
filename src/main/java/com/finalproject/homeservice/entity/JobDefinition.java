package com.finalproject.homeservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_definitions")
public class JobDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "jobDefinition")
    private List<Job> jobs;

    @ManyToMany
    @JoinTable(name = "job_definition_attributes",
            joinColumns = @JoinColumn(name = "job_definition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"))
    private List<Attribute> attributes;

    @OneToOne(mappedBy = "jobDefinition")
    private Expertise expertise;
}
