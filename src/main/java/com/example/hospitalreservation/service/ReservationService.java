package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ShowReservationResponse;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.TimeTable;
import com.example.hospitalreservation.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// 서비스 레이어에서 필요한 어노테이션을 작성해주세요.
@Slf4j
@Service
public class ReservationService {

    // 주입 받아야 객체를 작성해주세요.
    private final ReservationRepository reservationRepository;
    private final TimeTable timeTable;

    public ReservationService(ReservationRepository reservationRepository, TimeTable timeTable) {
        this.reservationRepository = reservationRepository;
        this.timeTable = timeTable;
    }

    // 모든 예약 리스트를 조회하는 코드를 작성해주세요.
    public List<ShowReservationResponse> getAllReservations() {
        log.info("Get all reservations Service");
        return reservationRepository.findAll().stream()
                .map(reservation-> ShowReservationResponse.from(reservation)).toList();
    }

    // 예약이 가능한지 검사 후 예약하기
    public void createReservation(Reservation reservation) throws Exception {
        try {
            reservation.setId(reservationRepository.getNextId());
            timeTable.enroll(reservation);
            reservationRepository.save(reservation);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    // 예약 취소 가능성을 검사 후 취소하기
    public void cancelReservation(Long id) throws Exception {
        try {
            // ToDo : 이 부분 로직 수정해야할 것 같음..
            Reservation reservation = reservationRepository.findById(id);
            reservationRepository.deleteById(id);
            timeTable.remove(id);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}
