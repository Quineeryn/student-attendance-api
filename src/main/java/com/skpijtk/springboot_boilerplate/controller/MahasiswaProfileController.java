package com.skpijtk.springboot_boilerplate.controller;

import com.skpijtk.springboot_boilerplate.dto.ApiResponse;
import com.skpijtk.springboot_boilerplate.dto.MahasiswaProfileResponseDTO;
import com.skpijtk.springboot_boilerplate.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/mahasiswa")
@PreAuthorize("hasRole('MAHASISWA')")
public class MahasiswaProfileController {

    private final AttendanceService attendanceService;

    @Autowired
    public MahasiswaProfileController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // API #16: GET /mahasiswa/profile
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<MahasiswaProfileResponseDTO>> getMyProfile(
            Authentication authentication, // Dapatkan user yang sedang login
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 10, sort = "attendanceDate", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable
    ) {
        String userEmail = authentication.getName(); // Dapatkan email user
        MahasiswaProfileResponseDTO profile = attendanceService.getMahasiswaProfile(userEmail, startDate, endDate, pageable);

        ApiResponse<MahasiswaProfileResponseDTO> response = new ApiResponse<>(
                profile,
                "Profil mahasiswa berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }
}