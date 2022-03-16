package com.finalproject.homeservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    private String description;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
    private Set<JobDefinition> jobDefinitions;

}
