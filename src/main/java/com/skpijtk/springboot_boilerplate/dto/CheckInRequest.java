package com.skpijtk.springboot_boilerplate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CheckInRequest {

    @NotBlank(message = "Catatan check-in tidak boleh kosong")
    @Size(max = 1000, message = "Catatan check-in maksimal 1000 karakter")
    private String notesCheckin;

    // Getters and Setters
    public String getNotesCheckin() {
        return notesCheckin;
    }

    public void setNotesCheckin(String notesCheckin) {
        this.notesCheckin = notesCheckin;
    }
}