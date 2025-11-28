package com.example.interviewscheduler.dto;

public class RescheduleRequest {
    private Long oldSlotId;
    private Long newSlotId;
    private Long candidateId;

    public Long getOldSlotId() {
        return oldSlotId;
    }

    public void setOldSlotId(Long oldSlotId) {
        this.oldSlotId = oldSlotId;
    }

    public Long getNewSlotId() {
        return newSlotId;
    }

    public void setNewSlotId(Long newSlotId) {
        this.newSlotId = newSlotId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}
