package com.example.interviewscheduler.repository;

import com.example.interviewscheduler.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
