package com.example.hospitalreservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String encodedPassword;

    public User() {}
    public User(String username, String encodedPassword) {
        this.username = username;
        this.encodedPassword = encodedPassword;
    }

    public String getEncodedPassword() { return encodedPassword; }
    public String getUsername() { return username; }
}
