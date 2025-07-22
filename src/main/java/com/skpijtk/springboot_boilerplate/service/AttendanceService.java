// File: src/main/java/com/skpijtk/springboot_boilerplate/service/AttendanceService.java
package com.skpijtk.springboot_boilerplate.service;

import com.skpijtk.springboot_boilerplate.dto.*;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface AttendanceService {

    /**
     * Melakukan proses check-in untuk mahasiswa yang sedang login.
     * @param userEmail Email dari mahasiswa yang terautentikasi (dari token JWT).
     * @param request DTO yang berisi catatan check-in.
     * @return DTO yang berisi detail hasil aksi check-in.
     */
    AttendanceActionResponse performCheckIn(String userEmail, CheckInRequest request);

    /**
     * Melakukan proses check-out untuk mahasiswa yang sedang login.
     * @param userEmail Email dari mahasiswa yang terautentikasi (dari token JWT).
     * @param request DTO yang berisi catatan check-out.
     * @return DTO yang berisi detail hasil aksi check-out.
     */
    AttendanceActionResponse performCheckOut(String userEmail, CheckOutRequest request);

    /**
     * Mengambil data profil lengkap mahasiswa beserta riwayat kehadirannya.
     * @param userEmail Email dari mahasiswa yang sedang login.
     * @param startDate Filter tanggal mulai (opsional).
     * @param endDate Filter tanggal akhir (opsional).
     * @param pageable Objek untuk paginasi riwayat kehadiran.
     * @return DTO yang berisi data profil dan riwayat kehadiran.
     */
    MahasiswaProfileResponseDTO getMahasiswaProfile(String userEmail, LocalDate startDate, LocalDate endDate, Pageable pageable);

    /**
     * Mengambil riwayat kehadiran untuk satu mahasiswa spesifik (untuk admin).
     * @param studentId ID dari mahasiswa yang riwayatnya ingin dilihat.
     * @param startDate Filter tanggal mulai (opsional).
     * @param endDate Filter tanggal akhir (opsional).
     * @param pageable Objek untuk paginasi.
     * @return DTO paginasi yang berisi daftar record kehadiran.
     */
    PageResponseDTO<AttendanceRecordDTO> getStudentAttendanceHistory(Long studentId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    PageResponseDTO<StudentAttendanceSummaryDTO> getFilteredAttendanceList(String studentName, LocalDate startDate, LocalDate endDate, Pageable pageable);

}