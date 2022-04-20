package com.finalproject.homeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attributes")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //telikeli
    private String property;

    @ManyToMany
    @JoinTable(name = "atribte_choices",
            joinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"))
    private List<Choice> choices;

    @ManyToMany(mappedBy = "attributes")
    private List<JobDefinition> jobDefinitions;

}
