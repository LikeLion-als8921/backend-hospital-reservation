package com.example.hospitalreservation.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() { return id; }

    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations = new ArrayList<>();
}
