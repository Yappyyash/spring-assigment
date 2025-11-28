package com.example.interviewscheduler.controller;

import com.example.interviewscheduler.dto.AvailabilityDto;
import com.example.interviewscheduler.dto.BookingRequest;
import com.example.interviewscheduler.dto.RescheduleRequest;
import com.example.interviewscheduler.model.Availability;
import com.example.interviewscheduler.model.Candidate;
import com.example.interviewscheduler.model.InterviewSlot;
import com.example.interviewscheduler.model.Interviewer;
import com.example.interviewscheduler.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping("/interviewers")
    public ResponseEntity<Interviewer> createInterviewer(@RequestBody Interviewer interviewer) {
        return ResponseEntity.ok(interviewService.createInterviewer(interviewer.getName(), interviewer.getEmail(),
                interviewer.getMaxInterviewsPerWeek()));
    }

    @PostMapping("/interviewers/{id}/availability")
    public ResponseEntity<Void> setAvailability(@PathVariable Long id,
            @RequestBody List<AvailabilityDto> availabilityDtos) {
        List<Availability> availabilities = availabilityDtos.stream().map(dto -> {
            Availability availability = new Availability();
            availability.setDayOfWeek(dto.getDayOfWeek());
            availability.setStartTime(dto.getStartTime());
            availability.setEndTime(dto.getEndTime());
            return availability;
        }).collect(Collectors.toList());

        interviewService.setAvailability(id, availabilities);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/slots/available")
    public ResponseEntity<List<InterviewSlot>> getAvailableSlots() {
        return ResponseEntity.ok(interviewService.getAvailableSlots());
    }

    @PostMapping("/candidates")
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return ResponseEntity.ok(interviewService.createCandidate(candidate.getName(), candidate.getEmail()));
    }

    @PostMapping("/slots/{slotId}/book")
    public ResponseEntity<InterviewSlot> bookSlot(@PathVariable Long slotId, @RequestBody BookingRequest request) {
        return ResponseEntity.ok(interviewService.bookSlot(slotId, request.getCandidateId()));
    }

    @PutMapping("/slots/reschedule")
    public ResponseEntity<InterviewSlot> rescheduleSlot(@RequestBody RescheduleRequest request) {
        return ResponseEntity.ok(
                interviewService.updateSlot(request.getOldSlotId(), request.getNewSlotId(), request.getCandidateId()));
    }
}
