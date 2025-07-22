package com.skpijtk.springboot_boilerplate.controller;

import com.skpijtk.springboot_boilerplate.dto.ApiResponse;
import com.skpijtk.springboot_boilerplate.dto.AuthResponse;
import com.skpijtk.springboot_boilerplate.dto.LoginRequest;
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
@RequestMapping("/mahasiswa") // Path dasar untuk mahasiswa
public class MahasiswaAuthController {

    private final AuthService authService;

    @Autowired
    public MahasiswaAuthController(AuthService authService) {
        this.authService = authService;
    }

    // API #15: POST /mahasiswa/login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> loginMahasiswa(@Valid @RequestBody LoginRequest loginRequest) {
        // Kita panggil kembali service method yang sama dengan login admin!
        AuthResponse authResponse = authService.loginUser(loginRequest);
        ApiResponse<AuthResponse> response = new ApiResponse<>(
                authResponse,
                "Login mahasiswa berhasil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}