package com.example.interviewscheduler.service;

import com.example.interviewscheduler.model.*;
import com.example.interviewscheduler.repository.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private InterviewSlotRepository interviewSlotRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Transactional
    public Interviewer createInterviewer(String name, String email, int maxInterviewsPerWeek) {
        Interviewer interviewer = new Interviewer();
        interviewer.setName(name);
        interviewer.setEmail(email);
        interviewer.setMaxInterviewsPerWeek(maxInterviewsPerWeek);
        return interviewerRepository.save(interviewer);
    }

    @Transactional
    public void setAvailability(Long interviewerId, List<Availability> availabilities) {
        Interviewer interviewer = interviewerRepository.findById(interviewerId)
                .orElseThrow(() -> new RuntimeException("Interviewer not found"));

        // Clear existing availabilities? Or append? Assuming replace for simplicity.
        // In a real app, we might need to be careful about existing slots.
        // For this exercise, I'll just save the new availabilities.

        for (Availability availability : availabilities) {
            availability.setInterviewer(interviewer);
            availabilityRepository.save(availability);
        }

        // Generate slots for the next 2 weeks
        generateSlotsForNextTwoWeeks(interviewer);
    }

    private void generateSlotsForNextTwoWeeks(Interviewer interviewer) {
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksLater = today.plusWeeks(2);

        List<Availability> availabilities = availabilityRepository.findByInterviewerId(interviewer.getId());

        for (LocalDate date = today; date.isBefore(twoWeeksLater); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            for (Availability availability : availabilities) {
                if (availability.getDayOfWeek() == dayOfWeek) {
                    // Check if slot already exists to avoid duplicates
                    if (!interviewSlotRepository.existsByInterviewerIdAndDateAndStartTime(interviewer.getId(), date,
                            availability.getStartTime())) {
                        InterviewSlot slot = new InterviewSlot();
                        slot.setInterviewer(interviewer);
                        slot.setDate(date);
                        slot.setStartTime(availability.getStartTime());
                        slot.setEndTime(availability.getEndTime());
                        slot.setStatus(InterviewSlot.SlotStatus.AVAILABLE);

                        interviewSlotRepository.save(slot);
                    }
                }
            }
        }
    }

    public List<InterviewSlot> getAvailableSlots() {
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksLater = today.plusWeeks(2);
        return interviewSlotRepository.findByStatusAndDateBetween(InterviewSlot.SlotStatus.AVAILABLE, today,
                twoWeeksLater);
    }

    @Transactional
    public InterviewSlot bookSlot(Long slotId, Long candidateId) {
        InterviewSlot slot = interviewSlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getStatus() == InterviewSlot.SlotStatus.BOOKED) {
            throw new RuntimeException("Slot already booked");
        }

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        // Check max interviews per week for the interviewer
        Interviewer interviewer = slot.getInterviewer();
        LocalDate date = slot.getDate();
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        long bookedCount = interviewSlotRepository.countBookedSlotsInWeek(interviewer.getId(),
                InterviewSlot.SlotStatus.BOOKED, startOfWeek, endOfWeek);

        if (bookedCount >= interviewer.getMaxInterviewsPerWeek()) {
            throw new RuntimeException("Interviewer has reached maximum interviews for this week");
        }

        slot.setCandidate(candidate);
        slot.setStatus(InterviewSlot.SlotStatus.BOOKED);
        return interviewSlotRepository.save(slot);
    }

    @Transactional
    public InterviewSlot updateSlot(Long oldSlotId, Long newSlotId, Long candidateId) {
        // Cancel old slot
        InterviewSlot oldSlot = interviewSlotRepository.findById(oldSlotId)
                .orElseThrow(() -> new RuntimeException("Old slot not found"));

        if (!oldSlot.getCandidate().getId().equals(candidateId)) {
            throw new RuntimeException("Candidate does not own this slot");
        }

        oldSlot.setCandidate(null);
        oldSlot.setStatus(InterviewSlot.SlotStatus.AVAILABLE);
        interviewSlotRepository.save(oldSlot);

        // Book new slot
        return bookSlot(newSlotId, candidateId);
    }

    @Transactional
    public Candidate createCandidate(String name, String email) {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setEmail(email);
        return candidateRepository.save(candidate);
    }

    public List<Interviewer> getAllInterviewers() {
        return interviewerRepository.findAll();
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}
