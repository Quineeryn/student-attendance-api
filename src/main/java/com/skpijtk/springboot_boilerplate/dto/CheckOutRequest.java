package com.skpijtk.springboot_boilerplate.dto; // Sesuaikan package

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CheckOutRequest {

    @NotBlank(message = "Catatan check-out tidak boleh kosong")
    @Size(max = 1000, message = "Catatan check-out maksimal 1000 karakter")
    private String notesCheckout;

    public String getNotesCheckout() {
        return notesCheckout;
    }

    public void setNotesCheckout(String notesCheckout) {
        this.notesCheckout = notesCheckout;
    }
}