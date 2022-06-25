package com.finalproject.homeservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attributes")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //telikeli
    private String property;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "atribte_choices",
            joinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id", referencedColumnName = "id"))
    private List<Choice> choices;

    @ManyToMany(mappedBy = "attributes")
    private List<JobDefinition> jobDefinitions;

    @OneToMany(mappedBy = "attribute")
    private List<JobAttributeChoice> jobAttributeChoices;


   /*@ManyToMany(mappedBy = "attributes")
    private List<Job> jobs;*/

}
