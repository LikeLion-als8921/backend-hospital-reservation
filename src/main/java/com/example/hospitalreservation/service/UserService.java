package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.UserDTO;
import com.example.hospitalreservation.model.User;
import com.example.hospitalreservation.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(UserDTO dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        // userName 이 같은 사람이 있음
        if(optionalUser.isPresent()) return false;

        String encodedPassword = dto.getPassword();
        User user = new User(dto.getUsername(), encodedPassword);
        userRepository.save(user);
        return true;
    }

    public boolean loginUser(UserDTO dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            String encodedPassword = dto.getPassword();
            return user.getEncodedPassword().equals(encodedPassword);
        }
        return false;
    }
}
