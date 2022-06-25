package com.finalproject.homeservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private Double price;
    private boolean accepted;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User professional;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;



}
