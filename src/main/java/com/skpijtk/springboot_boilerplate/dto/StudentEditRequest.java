package com.skpijtk.springboot_boilerplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StudentEditRequest {

    @NotBlank(message = "Nama mahasiswa tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Nama mahasiswa harus antara 3 dan 100 karakter")
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message = "Nama hanya boleh berisi huruf dan spasi (tidak boleh spasi di awal/akhir, atau spasi ganda)")
    private String studentName;

    @NotBlank(message = "NIM tidak boleh kosong")
    @Size(min = 9, max = 9, message = "NIM harus terdiri dari 9 angka")
    @Pattern(regexp = "[0-9]{9}", message = "NIM hanya boleh berisi angka")
    private String nim;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Format email tidak valid")
    @Size(max = 50, message = "Email maksimal 50 karakter")
    private String email;

    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}