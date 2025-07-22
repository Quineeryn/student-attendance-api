package com.skpijtk.springboot_boilerplate.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "appsettings")
public class Appsettings {

    @Id
    private Integer id = 1;

    @Column(name = "default_check_in_time", nullable = false)
    private LocalTime defaultCheckInTime;

    @Column(name = "default_check_out_time", nullable = false)
    private LocalTime defaultCheckOutTime;

    @Column(name = "check_in_late_tolerance_minutes", nullable = false)
    private Integer checkInLateToleranceMinutes;
    @Column(name = "check_out_late_tolerance_minutes", nullable = false)
    private Integer checkOutLateToleranceMinutes;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Appsettings() {
    }

    @PrePersist
    protected void onCreate() {
        this.updatedAt = LocalDateTime.now();
        if (this.id == null) {
            this.id = 1;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        // Biasanya ID ini tidak diubah, selalu 1
        if (id != null && id == 1) {
            this.id = id;
        }
    }

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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}