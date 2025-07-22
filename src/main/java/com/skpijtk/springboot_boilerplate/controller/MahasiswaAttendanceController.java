package com.skpijtk.springboot_boilerplate.controller;

import com.skpijtk.springboot_boilerplate.dto.ApiResponse;
import com.skpijtk.springboot_boilerplate.dto.AttendanceActionResponse;
import com.skpijtk.springboot_boilerplate.dto.CheckInRequest;
import com.skpijtk.springboot_boilerplate.dto.CheckOutRequest;
import com.skpijtk.springboot_boilerplate.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mahasiswa")
@PreAuthorize("hasRole('MAHASISWA')") // Hanya MAHASISWA yang bisa akses endpoint ini
public class MahasiswaAttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public MahasiswaAttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // API #17: POST /mahasiswa/checkin
    @PostMapping("/checkin")
    public ResponseEntity<ApiResponse<AttendanceActionResponse>> performCheckIn(
            Authentication authentication, // Objek ini di-inject otomatis oleh Spring Security
            @Valid @RequestBody CheckInRequest request
    ) {
        String userEmail = authentication.getName(); // Dapatkan email dari user yang login
        AttendanceActionResponse result = attendanceService.performCheckIn(userEmail, request);
        ApiResponse<AttendanceActionResponse> response = new ApiResponse<>(
                result,
                "Check-in berhasil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #18: POST /mahasiswa/checkout
    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<AttendanceActionResponse>> performCheckOut(
            Authentication authentication, // Objek ini juga di-inject otomatis
            @Valid @RequestBody CheckOutRequest request
    ) {
        String userEmail = authentication.getName(); // Dapatkan email dari user yang login
        AttendanceActionResponse result = attendanceService.performCheckOut(userEmail, request);
        ApiResponse<AttendanceActionResponse> response = new ApiResponse<>(
                result,
                "Check-out berhasil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }
}