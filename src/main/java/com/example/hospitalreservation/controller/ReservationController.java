package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import com.example.hospitalreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.
@Slf4j
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    // 주입 받아야 할 객체를 설정해주세요.
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 필요한 어노테이션을 작성해주세요.
    @GetMapping
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "index";
    }

    // 필요한 어노테이션을 작성해주세요.
    @GetMapping("/new")
    public String showReservationForm() {
        // 예약하기 페이지를 가져오는 코드를 작성해주세요.
        return "reservation_form";
    }

    // 필요한 어노테이션을 작성해주세요.
    @PostMapping
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam String reservationTime) {
        // 예약을 진행하는 코드를 작성해주세요.
        LocalDateTime localDateTime = LocalDateTime.parse(reservationTime);

        reservationService.createReservation(doctorId, patientId, localDateTime);
        return "redirect:/reservations";
    }

    @PostMapping("/delete/{id}")
    // 필요한 어노테이션을 작성해주세요.
    public String cancelReservation(@PathVariable Long id) {
        // 예약을 취소하는 코드를 작성해주세요.
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}