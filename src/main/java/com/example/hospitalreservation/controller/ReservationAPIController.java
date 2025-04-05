package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.CreateReservationRequest;
import com.example.hospitalreservation.dto.CreateReservationResponse;
import com.example.hospitalreservation.dto.ShowReservationResponse;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("")
    public ResponseEntity<List<ShowReservationResponse>> getReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping("")
    public ResponseEntity<CreateReservationResponse> reservation(@RequestBody CreateReservationRequest dto) {
        Reservation reservation = new Reservation(
                dto.getPatientId(),
                dto.getDoctorId(),
                dto.getReservationStartTime(),
                dto.getReservationEndTime(),
                dto.getReason());
        log.info("Reservation created");
        CreateReservationResponse response;
        // 예약에 성공했을 경우
        try {
            reservationService.createReservation(reservation);
            response = CreateReservationResponse.success(1001L, "예약이 완료되었습니다.", 10000L);
            log.info("예약이 완료되었습니다.");
            return ResponseEntity.ok(response);
        }
        // 예약에 실패했을 경우
        catch (Exception e) {
            response = CreateReservationResponse.failure("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            log.info("{}", e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> cancelReservation(@RequestBody Map<String, Object> reservationData, @PathVariable Long id) {
        String cancelReason = reservationData.get("cancelReason").toString();
        log.info("cancel reason: {}", cancelReason);
        Map<String, Object> map = new HashMap<>();

        // 예약 취소 성공
        try {
            reservationService.cancelReservation(id);
            map.put("message", "예약이 취소되었습니다.");
            log.info("취소 사유 : {}", cancelReason);
        }
        // 예약이 존재하지 않는 경우
        catch (Exception e) {
            map.put("error", e.getMessage());
        }
        return map;
    }
}
