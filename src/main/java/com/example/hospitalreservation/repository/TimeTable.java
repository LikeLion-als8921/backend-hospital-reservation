package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.ReservationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    public void removeTime(int hour){
        for(Integer i : reservationTime){
            log.info(i.toString());
        }
        for(int i = 0 ; i < reservationTime.size(); i++){
            if(reservationTime.get(i) == hour){
                reservationTime.remove(i);
            }
        }
        log.info("DELETE");
        for(Integer i : reservationTime){
            log.info(i.toString());
        }
    }
}
