package com.finalproject.homeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expertites")
public class Expertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "expertise_name")
    private String expertiseName;
    private String description;

    @OneToMany(mappedBy = "expertise" ,cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "job_definition_id")
    private JobDefinition jobDefinition;
}
