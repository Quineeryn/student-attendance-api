package com.skpijtk.springboot_boilerplate.controller;

import com.skpijtk.springboot_boilerplate.dto.*;
import com.skpijtk.springboot_boilerplate.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    private final AuthService authService;

    @Autowired
    public AdminAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AdminSignupResponseDTO>> registerAdmin(@Valid @RequestBody AdminSignupRequest signupRequest) {
        AdminSignupResponseDTO registeredAdmin = authService.registerAdmin(signupRequest);
        ApiResponse<AdminSignupResponseDTO> response = new ApiResponse<>(
                registeredAdmin,
                "Admin berhasil terdaftar. [T-SUCC-001]", // Contoh pesan dari spek
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> loginAdmin(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.loginUser(loginRequest);
        ApiResponse<AuthResponse> response = new ApiResponse<>(
                authResponse,
                "Login berhasil. [T-SUCC-002]",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}