package com.skpijtk.springboot_boilerplate.controller;

import com.skpijtk.springboot_boilerplate.dto.ApiResponse;
import com.skpijtk.springboot_boilerplate.dto.ResumeCheckinResponseDTO;
import com.skpijtk.springboot_boilerplate.dto.TotalMahasiswaResponseDTO;
import com.skpijtk.springboot_boilerplate.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public AdminDashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // API #4: GET /admin/total_mahasiswa
    @GetMapping("/total_mahasiswa")
    public ResponseEntity<ApiResponse<TotalMahasiswaResponseDTO>> getTotalMahasiswa() {
        TotalMahasiswaResponseDTO data = dashboardService.getTotalMahasiswa();
        ApiResponse<TotalMahasiswaResponseDTO> response = new ApiResponse<>(
                data,
                "Total mahasiswa berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #5: GET /admin/resume_checkin
    @GetMapping("/resume_checkin")
    public ResponseEntity<ApiResponse<ResumeCheckinResponseDTO>> getResumeCheckin() {
        ResumeCheckinResponseDTO data = dashboardService.getCheckinResume();
        ApiResponse<ResumeCheckinResponseDTO> response = new ApiResponse<>(
                data,
                "Ringkasan check-in berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }
}