package com.finalproject.homeservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private List<Address> addresses = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Offer> offers = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "expertise_id")
    private Expertise expertise;

}
