// File: src/main/java/com/skpijtk/springboot_boilerplate/security/CustomUserDetailsService.java
package com.skpijtk.springboot_boilerplate.security;

import com.skpijtk.springboot_boilerplate.model.User;
import com.skpijtk.springboot_boilerplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service // Tandai sebagai service agar bisa di-inject
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Cari user di database berdasarkan email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User tidak ditemukan dengan email: " + email));

        // 2. Buat collection of authorities (roles)
        // Spring Security membutuhkan peran dalam format GrantedAuthority.
        // Kita bungkus peran dari user kita (misal: "ADMIN", "MAHASISWA") ke dalam SimpleGrantedAuthority.
        Collection<? extends GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        // 3. Buat dan kembalikan objek UserDetails dari Spring Security
        // Objek ini berisi informasi yang dibutuhkan Spring Security untuk melakukan autentikasi dan otorisasi.
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),          // username
                user.getPassword(),       // password yang sudah di-hash dari database
                authorities               // daftar peran/hak akses
        );
    }
}