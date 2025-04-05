package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.model.ReservationException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    public Long getNextId() {return nextId;}
    // 모든 예약 엔티티를 조회하는 코드를 작성해주세요.
    public List<Reservation> findAll() {
        return reservations;
    }

    public Reservation findById(Long id) throws Exception {
        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(id)) {
                return reservation;
            }
        }
        return null;
    }

    // 예약 엔티티를 저장하는 코드를 작성해주세요.
    public Reservation save(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.add(reservation);
        return reservation;
    }

    // 예약 엔티티를 삭제하는 코드를 작성해주세요.
    public void deleteById(Long id) throws Exception
    {
        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(id)) {
                reservations.remove(reservation);
                return;
            }
        }
        throw new Exception(ReservationException.NOT_EXIST_RESERVATION.getErrorMessage());
    }
}
