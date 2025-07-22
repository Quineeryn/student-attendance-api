package com.skpijtk.springboot_boilerplate.dto;

public class ResumeCheckinResponseDTO {

    private long totalMahasiswa;
    private long totalCheckin;
    private long totalBelumCheckin;
    private long totalTelatCheckin;

    public ResumeCheckinResponseDTO(long totalMahasiswa, long totalCheckin, long totalBelumCheckin, long totalTelatCheckin) {
        this.totalMahasiswa = totalMahasiswa;
        this.totalCheckin = totalCheckin;
        this.totalBelumCheckin = totalBelumCheckin;
        this.totalTelatCheckin = totalTelatCheckin;
    }

    // Getters and Setters
    public long getTotalMahasiswa() {
        return totalMahasiswa;
    }

    public void setTotalMahasiswa(long totalMahasiswa) {
        this.totalMahasiswa = totalMahasiswa;
    }

    public long getTotalCheckin() {
        return totalCheckin;
    }

    public void setTotalCheckin(long totalCheckin) {
        this.totalCheckin = totalCheckin;
    }

    public long getTotalBelumCheckin() {
        return totalBelumCheckin;
    }

    public void setTotalBelumCheckin(long totalBelumCheckin) {
        this.totalBelumCheckin = totalBelumCheckin;
    }

    public long getTotalTelatCheckin() {
        return totalTelatCheckin;
    }

    public void setTotalTelatCheckin(long totalTelatCheckin) {
        this.totalTelatCheckin = totalTelatCheckin;
    }
}