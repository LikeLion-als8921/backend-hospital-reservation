package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.repository.DoctorRepo;
import com.example.hospitalreservation.repository.PatientRepo;
import com.example.hospitalreservation.repository.TimeTable;
import com.example.hospitalreservation.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }
}
