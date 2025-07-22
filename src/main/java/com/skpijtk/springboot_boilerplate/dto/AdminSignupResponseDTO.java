package com.skpijtk.springboot_boilerplate.dto;

public class AdminSignupResponseDTO {
    private String email;

    public AdminSignupResponseDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}