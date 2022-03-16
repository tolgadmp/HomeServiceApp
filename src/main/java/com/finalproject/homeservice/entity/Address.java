package com.finalproject.homeservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String distinc;
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
