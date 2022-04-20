package com.finalproject.homeservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seleciton;

    @ManyToMany(mappedBy = "choices")
    private List<Attribute> attributes;




}
