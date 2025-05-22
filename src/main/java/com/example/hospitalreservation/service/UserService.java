package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.UserDTO;
import com.example.hospitalreservation.model.User;
import com.example.hospitalreservation.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(UserDTO dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        // userName 이 같은 사람이 있음
        if(optionalUser.isPresent()) return false;
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(dto.getUsername(), encodedPassword);
        userRepository.save(user);
        return true;
    }

    public boolean loginUser(UserDTO dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(dto.getPassword(), user.getEncodedPassword());
        }
        return false;
    }
}
