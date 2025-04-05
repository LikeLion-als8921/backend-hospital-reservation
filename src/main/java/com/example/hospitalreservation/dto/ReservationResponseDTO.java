package com.example.hospitalreservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponseDTO {
    private Long reservationId;
    private String message;
    private Long calculatedFee;
    private String error;

    public ReservationResponseDTO(Long reservationId, String message, Long calculatedFee) {
        this.reservationId = reservationId;
        this.message = message;
        this.calculatedFee = calculatedFee;
    }
    public ReservationResponseDTO(String errorMessage) {
        this.error = errorMessage;
    }

    public Long getCalculatedFee() {return calculatedFee;}
    public String getError() {return error;}
    public Long getReservationId() {return reservationId;}
    public String getMessage() {return message;}
}
