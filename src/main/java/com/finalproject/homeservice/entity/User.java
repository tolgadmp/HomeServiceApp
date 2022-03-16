package com.finalproject.homeservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "company_name")
    private String companyName;
    private String email;
    private String password;
    private String phone;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Job> jobs = new HashSet<>();
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private Set<Offer> offers = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "expertise_id")
    private Expertise expertise;

}
