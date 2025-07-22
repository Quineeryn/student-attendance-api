// File: src/main/java/com/skpijtk/springboot_boilerplate/service/impl/AppSettingsServiceImpl.java
package com.skpijtk.springboot_boilerplate.service.impl;

import com.skpijtk.springboot_boilerplate.dto.AppSettingsDTO;
import com.skpijtk.springboot_boilerplate.exception.ResourceNotFoundException;
import com.skpijtk.springboot_boilerplate.model.Appsettings;
import com.skpijtk.springboot_boilerplate.repository.AppSettingsRepository;
import com.skpijtk.springboot_boilerplate.service.AppSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppSettingsServiceImpl implements AppSettingsService {

    private final AppSettingsRepository appSettingsRepository;

    @Autowired
    public AppSettingsServiceImpl(AppSettingsRepository appSettingsRepository) {
        this.appSettingsRepository = appSettingsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AppSettingsDTO getAppSettings() {
        Appsettings settings = appSettingsRepository.findById(1) // ID selalu 1 untuk settings
                .orElseThrow(() -> new ResourceNotFoundException("Pengaturan aplikasi tidak ditemukan."));
        return mapToDTO(settings);
    }

    @Override
    @Transactional
    public AppSettingsDTO updateAppSettings(AppSettingsDTO appSettingsDTO) {
        Appsettings settings = appSettingsRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Pengaturan aplikasi tidak ditemukan untuk diperbarui."));

        // Update nilai dari DTO ke Entity
        settings.setDefaultCheckInTime(appSettingsDTO.getDefaultCheckInTime());
        settings.setDefaultCheckOutTime(appSettingsDTO.getDefaultCheckOutTime());
        settings.setCheckInLateToleranceMinutes(appSettingsDTO.getCheckInLateToleranceMinutes());
        settings.setCheckOutLateToleranceMinutes(appSettingsDTO.getCheckOutLateToleranceMinutes());
        // Kolom updatedAt akan diupdate otomatis oleh @PreUpdate

        Appsettings updatedSettings = appSettingsRepository.save(settings);

        return mapToDTO(updatedSettings);
    }

    // Helper method untuk konversi Entity ke DTO
    private AppSettingsDTO mapToDTO(Appsettings settings) {
        return new AppSettingsDTO(
                settings.getDefaultCheckInTime(),
                settings.getDefaultCheckOutTime(),
                settings.getCheckInLateToleranceMinutes(),
                settings.getCheckOutLateToleranceMinutes()
        );
    }
}