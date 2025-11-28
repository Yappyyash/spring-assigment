package com.example.interviewscheduler.model;

import javax.persistence.*;
import java.util.List;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaxInterviewsPerWeek() {
        return maxInterviewsPerWeek;
    }

    public void setMaxInterviewsPerWeek(int maxInterviewsPerWeek) {
        this.maxInterviewsPerWeek = maxInterviewsPerWeek;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}
