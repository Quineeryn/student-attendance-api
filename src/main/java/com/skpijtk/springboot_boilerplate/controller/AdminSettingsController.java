package com.skpijtk.springboot_boilerplate.controller;

import com.skpijtk.springboot_boilerplate.dto.ApiResponse;
import com.skpijtk.springboot_boilerplate.dto.AppSettingsDTO;
import com.skpijtk.springboot_boilerplate.service.AppSettingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system-settings")
@PreAuthorize("hasRole('ADMIN')")
public class AdminSettingsController {

    private final AppSettingsService appSettingsService;

    @Autowired
    public AdminSettingsController(AppSettingsService appSettingsService) {
        this.appSettingsService = appSettingsService;
    }

    // API #13: GET /admin/system-settings
    @GetMapping
    public ResponseEntity<ApiResponse<AppSettingsDTO>> getAppSettings() {
        AppSettingsDTO settings = appSettingsService.getAppSettings();
        ApiResponse<AppSettingsDTO> response = new ApiResponse<>(
                settings,
                "Pengaturan sistem berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #14: PUT /admin/system-settings
    @PutMapping
    public ResponseEntity<ApiResponse<AppSettingsDTO>> updateAppSettings(@Valid @RequestBody AppSettingsDTO request) {
        AppSettingsDTO updatedSettings = appSettingsService.updateAppSettings(request);
        ApiResponse<AppSettingsDTO> response = new ApiResponse<>(
                updatedSettings,
                "Pengaturan sistem berhasil diperbarui.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }
}