package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class ReservationTime
{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ReservationTime(Reservation reservation) {
        startTime = reservation.getReservationTime();
        endTime = startTime.plusHours(1);
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public boolean isConflict(ReservationTime other){
        // 시간이 겹치는 기준 판단하기
        // 1. 새로 들어온 endTime 이 기존 시간의 startTime 보다 빠를 경우
        // 2. 새로 들어온 startTime 이 기존 시간의 endTime 보다 느릴 경우

        return other.getEndTime().isBefore(startTime)
                || other.getStartTime().isBefore(endTime);
    }
}
