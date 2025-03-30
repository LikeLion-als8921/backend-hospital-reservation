package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import com.example.hospitalreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/api/reservations")
public class ReservationAPIController {

    private final ReservationService reservationService;
    public ReservationAPIController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


//    @GetMapping
//    public Map<String, Object> getReservationCount() {
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("count",reservationService.getAllReservations().size());
//        return map;
//    }

    @PostMapping("")
    public Map<String, Object> reservation(@RequestBody Map<String, Object> reservationData) {
        Map<String, Object> map = new HashMap<>();
        Long patientId = ((Number) reservationData.get("patientId")).longValue();
        Long doctorId = ((Number) reservationData.get("doctorId")).longValue();
        int reservationTime = LocalTime.parse(reservationData.get("reservationTime").toString()).getHour();

        Reservation reservation = new Reservation(doctorId, patientId, null);

        // 예약에 성공했을 경우
        try {
            reservationService.createReservation(reservation, reservationTime);
            map.put("reservationId", 1001);
            map.put("message", "예약이 완료되었습니다.");
        }
        // 예약에 실패했을 경우
        catch (Exception e) {
            log.error(e.getMessage());
            map.put("error","해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
        }
        return map;
    }


    @DeleteMapping("/{id}")
    public Map<String, Object> cancelReservation(@RequestBody Map<String, Object> reservationData, @PathVariable Long id) {
        String cancelReason = reservationData.get("cancelReason").toString();
        Map<String, Object> map = new HashMap<>();

        // 예약 취소 성공
        try {
            reservationService.cancelReservation(id);
            map.put("message", "예약이 취소되었습니다.");
            log.info("취소 사유 : {}", cancelReason);
        }
        // 예약이 존재하지 않는 경우
        catch (Exception e) {
            map.put("error", "존재하지 않는 예약입니다.");
        }
        return map;
    }
}
