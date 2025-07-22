package com.skpijtk.springboot_boilerplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminSignupRequest {

    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(max = 255, message = "Nama maksimal 255 karakter")
    private String name;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Format email tidak valid")
    @Size(max = 255, message = "Email maksimal 255 karakter")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 6, max = 50, message = "Password minimal 6 karakter dan maksimal 50 karakter")
    private String password;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}