package com.finalproject.homeservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attributes")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //telikeli
    private String property;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL)
    private Set<Choice> choices = new HashSet<>();

}
