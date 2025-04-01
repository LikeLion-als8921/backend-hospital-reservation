package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "index";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form";
    }

    @PostMapping
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam LocalDateTime reservationTime) {
        Reservation reservation = new Reservation(doctorId, patientId, reservationTime);
        try {
            reservationService.createReservation(reservation);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/reservations";
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id) {
        try {
            reservationService.cancelReservation(id);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/reservations";
    }
}