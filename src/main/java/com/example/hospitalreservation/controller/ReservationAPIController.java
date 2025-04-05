package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.*;
import com.example.hospitalreservation.enums.ReservationType;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/api/reservations")
public class ReservationAPIController {

    private final ReservationService reservationService;
    public ReservationAPIController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 리스트 조회
    @GetMapping("")
    public ResponseEntity<List<ShowReservationResponse>> getReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    // 예약 등록
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
            reservation.setFee(ReservationType.calculateFee(dto.getReason()));
            reservationService.createReservation(reservation);
            response = CreateReservationResponse.success(reservation.getId(), "예약이 완료되었습니다.", reservation.getFee());
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

    // 예약 취소
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteReservationResponse> cancelReservation(@RequestBody DeleteReservationRequest dto, @PathVariable Long id) {
        String cancelReason = dto.getCancelReason();
        log.info("cancel reason: {}", cancelReason);
        DeleteReservationResponse response;

        // 예약 취소 성공
        try {
            reservationService.cancelReservation(id);
            response = DeleteReservationResponse.success("예약이 취소되었습니다.");
            log.info("취소 사유 : {}", cancelReason);
            return ResponseEntity.ok(response);
        }
        // 예약이 존재하지 않는 경우
        catch (Exception e) {
            response = DeleteReservationResponse.failure(e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }
}
