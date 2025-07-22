package com.skpijtk.springboot_boilerplate.service.impl;

import com.skpijtk.springboot_boilerplate.security.JwtTokenProvider;
import com.skpijtk.springboot_boilerplate.dto.AdminSignupRequest;
import com.skpijtk.springboot_boilerplate.dto.AdminSignupResponseDTO;
import com.skpijtk.springboot_boilerplate.dto.AuthResponse;
import com.skpijtk.springboot_boilerplate.dto.LoginRequest;
import com.skpijtk.springboot_boilerplate.exception.EmailAlreadyExistsException;
import com.skpijtk.springboot_boilerplate.model.Role;
import com.skpijtk.springboot_boilerplate.model.User;
import com.skpijtk.springboot_boilerplate.repository.UserRepository;
import com.skpijtk.springboot_boilerplate.service.AuthService;
// import com.proyekmu.attendanceapp.security.JwtTokenProvider; // Akan kita buat nanti

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public AdminSignupResponseDTO registerAdmin(AdminSignupRequest adminSignupRequest) {
        if (userRepository.existsByEmail(adminSignupRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + adminSignupRequest.getEmail() + "' sudah terdaftar.");
        }

        User admin = new User();
        admin.setName(adminSignupRequest.getName());
        admin.setEmail(adminSignupRequest.getEmail());

        admin.setPassword(passwordEncoder.encode(adminSignupRequest.getPassword()));
        admin.setRole(Role.ADMIN);

        User savedAdmin = userRepository.save(admin);

        return new AdminSignupResponseDTO(savedAdmin.getEmail());
    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan setelah autentikasi berhasil"));

        String token = jwtTokenProvider.generateToken(authentication);

        return new AuthResponse(user.getId(), token, user.getName(), user.getRole().name());
    }
}