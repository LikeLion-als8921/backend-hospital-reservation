package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.UserDTO;
import com.example.hospitalreservation.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // 회원가입 API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto) {
        try {
            userService.registerUser(dto);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 실패: " + e.getMessage());
        }
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO dto) {
        boolean success = userService.loginUser(dto);
        if (success) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호 오류");
        }
    }
}
