package com.skpijtk.springboot_boilerplate.dto;

public class AdminProfileResponse {

    private String name;
    private String role;
    private String time;

    public AdminProfileResponse(String name, String role, String time) {
        this.name = name;
        this.role = role;
        this.time = time;
    }

    public AdminProfileResponse() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}