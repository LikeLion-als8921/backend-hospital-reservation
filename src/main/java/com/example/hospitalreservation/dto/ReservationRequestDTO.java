package com.example.hospitalreservation.dto;

import java.time.LocalDateTime;

public class ReservationRequestDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private Long fee;

    public Long getId() {return id;}
    public Long getDoctorId() {return doctorId;}
    public Long getPatientId() {return patientId;}
    public LocalDateTime getReservationStartTime() {return reservationStartTime;}
    public LocalDateTime getReservationEndTime() {return reservationEndTime;}
    public Long getFee() {return fee;}
}
