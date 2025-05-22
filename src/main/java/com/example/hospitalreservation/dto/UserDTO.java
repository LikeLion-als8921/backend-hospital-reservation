package com.example.hospitalreservation.dto;

import java.time.LocalDateTime;

public class UserDTO {


    private String username;
    private String password;

    public UserDTO(String username, String encodedPassword) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
