// File: src/main/java/com/proyekmu/attendanceapp/service/AuthService.java
package com.skpijtk.springboot_boilerplate.service; // Sesuaikan package

import com.skpijtk.springboot_boilerplate.dto.AdminSignupRequest;
import com.skpijtk.springboot_boilerplate.dto.AdminSignupResponseDTO; // DTO baru
import com.skpijtk.springboot_boilerplate.dto.AuthResponse;
import com.skpijtk.springboot_boilerplate.dto.LoginRequest;

public interface AuthService {

    /**
     * Mendaftarkan admin baru.
     * @param adminSignupRequest Data admin yang akan didaftarkan.
     * @return AdminSignupResponseDTO yang berisi email admin yang terdaftar.
     * @throws com.skpijtk.springboot_boilerplate.exception.EmailAlreadyExistsException jika email sudah terdaftar.
     */
    AdminSignupResponseDTO registerAdmin(AdminSignupRequest adminSignupRequest);

    /**
     * Melakukan login untuk pengguna (Admin atau Mahasiswa).
     * @param loginRequest Kredensial login (email dan password).
     * @return AuthResponse yang berisi token JWT dan detail pengguna.
     * @throws org.springframework.security.core.AuthenticationException jika autentikasi gagal.
     */
    AuthResponse loginUser(LoginRequest loginRequest);
}