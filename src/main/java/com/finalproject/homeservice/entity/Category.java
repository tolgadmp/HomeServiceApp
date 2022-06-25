package com.finalproject.homeservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private List<JobDefinition> jobDefinitions = new ArrayList<>();

}
