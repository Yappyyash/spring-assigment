package com.example.interviewscheduler.repository;

import com.example.interviewscheduler.model.InterviewSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface InterviewSlotRepository extends JpaRepository<InterviewSlot, Long> {
    List<InterviewSlot> findByInterviewerIdAndDateBetween(Long interviewerId, LocalDate startDate, LocalDate endDate);

    List<InterviewSlot> findByStatusAndDateBetween(InterviewSlot.SlotStatus status, LocalDate startDate,
            LocalDate endDate);

    boolean existsByInterviewerIdAndDateAndStartTime(Long interviewerId, LocalDate date, LocalTime startTime);

    @Query("SELECT COUNT(s) FROM InterviewSlot s WHERE s.interviewer.id = :interviewerId AND s.status = :status AND s.date BETWEEN :startDate AND :endDate")
    long countBookedSlotsInWeek(@Param("interviewerId") Long interviewerId,
            @Param("status") InterviewSlot.SlotStatus status, @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
