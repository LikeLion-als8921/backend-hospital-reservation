package com.example.hospitalreservation.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String reason;
    private Long fee;

    @ManyToOne @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Reservation() {

    }

    public Reservation(Doctor doctor, Patient patient, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String reason) {
        this.doctor = doctor;
        this.patient = patient;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.reason = reason;
    }

    public Long getId() {return id;}
    public Long getDoctorId() {return doctor.getId();}
    public Long getPatientId() {return patient.getId();}
    public LocalDateTime getReservationStartTime() {return reservationStartTime;}
    public LocalDateTime getReservationEndTime() {return reservationEndTime;}
    public String getReason() {return reason;}
    public Long getFee() {return fee;}

    public void setId(Long id) {this.id = id;}
    public void setFee(Long fee) {this.fee = fee;}
}
