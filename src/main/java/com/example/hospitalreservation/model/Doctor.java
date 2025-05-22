package com.example.hospitalreservation.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialization;

    // Doctor 클래스를 사용하지 않기 때문에 임시로 static으로 startHour, endHour를 설정
    private static int startHour = 9;
    private static int endHour = 17;

    public Long getId() { return id; }

    public static int getStartHour() { return startHour; }
    public static int getEndHour() { return endHour; }

    @OneToMany(mappedBy = "doctor")
    private List<Reservation> reservations = new ArrayList<>();

}