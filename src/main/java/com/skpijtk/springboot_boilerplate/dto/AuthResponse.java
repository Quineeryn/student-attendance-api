package com.skpijtk.springboot_boilerplate.dto;


public class AuthResponse {

    private Long idUser;
    private String token;
    private String name;
    private String role;

    public AuthResponse(Long idUser, String token, String name, String role) {
        this.idUser = idUser;
        this.token = token;
        this.name = name;
        this.role = role;
    }

    // Getters and Setters
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
}