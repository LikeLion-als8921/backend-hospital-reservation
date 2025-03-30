package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.ReservationException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeTable {
    private final List<Integer> reservationTime = new ArrayList<>();

    public void addTime(int hour) throws Exception {
        if(hour < Doctor.getStartHour() || hour > Doctor.getEndHour()){
            throw new Exception(ReservationException.NOT_AVAILABLE_TIME.getErrorMessage());
        }
        if(reservationTime.contains(hour)){
            throw new Exception(ReservationException.RESERVATION_TIME_ALREADY_EXISTS.getErrorMessage());
        }
        reservationTime.add(hour);
    }
}
