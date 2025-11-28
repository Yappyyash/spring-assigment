package com.example.interviewscheduler.repository;

import com.example.interviewscheduler.model.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {
}
