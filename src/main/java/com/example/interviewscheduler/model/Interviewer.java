package com.example.interviewscheduler.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Interviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Maximum number of interviews per week
    private int maxInterviewsPerWeek;

    @OneToMany(mappedBy = "interviewer", cascade = CascadeType.ALL)
    private List<Availability> availabilities;
}
