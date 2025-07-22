package com.skpijtk.springboot_boilerplate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public class AppSettingsDTO {

    @NotNull(message = "Default jam check-in tidak boleh kosong")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime defaultCheckInTime;

    @NotNull(message = "Default jam check-out tidak boleh kosong")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime defaultCheckOutTime;

    @NotNull(message = "Toleransi keterlambatan check-in tidak boleh kosong")
    @Min(value = 0, message = "Toleransi keterlambatan check-in minimal 0 menit")
    private Integer checkInLateToleranceMinutes;

    @NotNull(message = "Toleransi keterlambatan check-out tidak boleh kosong")
    @Min(value = 0, message = "Toleransi keterlambatan check-out minimal 0 menit")
    private Integer checkOutLateToleranceMinutes; // Sesuai DDL & Entity

    public AppSettingsDTO() {
    }

    public AppSettingsDTO(LocalTime defaultCheckInTime, LocalTime defaultCheckOutTime, Integer checkInLateToleranceMinutes, Integer checkOutLateToleranceMinutes) {
        this.defaultCheckInTime = defaultCheckInTime;
        this.defaultCheckOutTime = defaultCheckOutTime;
        this.checkInLateToleranceMinutes = checkInLateToleranceMinutes;
        this.checkOutLateToleranceMinutes = checkOutLateToleranceMinutes;
    }

    // Getters and Setters
    public LocalTime getDefaultCheckInTime() {
        return defaultCheckInTime;
    }

    public void setDefaultCheckInTime(LocalTime defaultCheckInTime) {
        this.defaultCheckInTime = defaultCheckInTime;
    }

    public LocalTime getDefaultCheckOutTime() {
        return defaultCheckOutTime;
    }

    public void setDefaultCheckOutTime(LocalTime defaultCheckOutTime) {
        this.defaultCheckOutTime = defaultCheckOutTime;
    }

    public Integer getCheckInLateToleranceMinutes() {
        return checkInLateToleranceMinutes;
    }

    public void setCheckInLateToleranceMinutes(Integer checkInLateToleranceMinutes) {
        this.checkInLateToleranceMinutes = checkInLateToleranceMinutes;
    }

    public Integer getCheckOutLateToleranceMinutes() {
        return checkOutLateToleranceMinutes;
    }

    public void setCheckOutLateToleranceMinutes(Integer checkOutLateToleranceMinutes) {
        this.checkOutLateToleranceMinutes = checkOutLateToleranceMinutes;
    }
}