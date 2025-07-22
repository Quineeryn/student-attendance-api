// File: src/main/java/com/skpijtk/springboot_boilerplate/service/StudentService.java
package com.skpijtk.springboot_boilerplate.service;

import com.skpijtk.springboot_boilerplate.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable; // <-- TAMBAHKAN IMPORT INI
import java.time.LocalDate; // <-- TAMBAHKAN IMPORT INI

public interface StudentService {

    /**
     * Mendaftarkan atau membuat data mahasiswa baru.
     * Ini akan melibatkan pembuatan User dan Student.
     * @param request DTO yang berisi data mahasiswa baru.
     * @return DTO yang berisi detail mahasiswa yang berhasil dibuat.
     */
    StudentDetailResponse createStudent(StudentCreationRequest request);

    /**
     * Mendapatkan detail satu mahasiswa berdasarkan ID-nya.
     * @param studentId ID dari mahasiswa yang dicari.
     * @return DTO yang berisi detail mahasiswa.
     */
    StudentDetailResponse getStudentById(Long studentId);

    /**
     * Memperbarui data mahasiswa yang sudah ada.
     * @param studentId ID dari mahasiswa yang akan diupdate.
     * @param request DTO yang berisi data baru untuk mahasiswa.
     * @return DTO yang berisi detail mahasiswa setelah diupdate.
     */
    StudentDetailResponse updateStudent(Long studentId, StudentEditRequest request);

    /**
     * Menghapus data mahasiswa berdasarkan ID-nya.
     * @param studentId ID dari mahasiswa yang akan dihapus.
     * @return DTO yang berisi detail singkat mahasiswa yang berhasil dihapus.
     */
    StudentDeleteResponse deleteStudent(Long studentId);

    /**
     * Mendapatkan daftar semua mahasiswa dengan paginasi dan filter dinamis.
     * @param studentName Filter nama mahasiswa (opsional).
     * @param startDate Filter tanggal mulai kehadiran (opsional).
     * @param endDate Filter tanggal akhir kehadiran (opsional).
     * @param pageable Objek untuk paginasi (halaman, ukuran, sorting).
     * @return DTO yang berisi daftar mahasiswa dan info paginasi.
     */
    PageResponseDTO<StudentDetailResponse> getAllStudents(String studentName, LocalDate startDate, LocalDate endDate, Pageable pageable);

}