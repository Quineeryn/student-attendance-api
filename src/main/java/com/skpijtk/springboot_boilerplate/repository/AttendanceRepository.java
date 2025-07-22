package com.skpijtk.springboot_boilerplate.repository;

import com.skpijtk.springboot_boilerplate.model.Attendance;
import com.skpijtk.springboot_boilerplate.model.CheckInStatus;
import com.skpijtk.springboot_boilerplate.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    Optional<Attendance> findByStudentAndAttendanceDate(Student student, LocalDate attendanceDate);

    boolean existsByStudentAndAttendanceDate(Student student, LocalDate attendanceDate);

    List<Attendance> findByStudent(Student student);

    /**
     * Mengecek apakah ada data kehadiran yang terkait dengan Student tertentu.
     * Berguna untuk validasi sebelum menghapus data mahasiswa.
     * @param student Objek Student yang akan dicek.
     * @return true jika ada, false jika tidak ada.
     */
    boolean existsByStudent(Student student);

    /**
     * Menghitung jumlah record kehadiran pada tanggal tertentu.
     * @param date Tanggal yang akan dihitung.
     * @return Jumlah record.
     */
    long countByAttendanceDate(LocalDate date);

    /**
     * Menghitung jumlah record kehadiran pada tanggal tertentu dengan status check-in tertentu.
     * @param date Tanggal yang akan dihitung.
     * @param status Status check-in (TEPAT_WAKTU atau TERLAMBAT).
     * @return Jumlah record.
     */
    long countByAttendanceDateAndCheckInStatus(LocalDate date, CheckInStatus status);
    @Query("SELECT a FROM Attendance a WHERE a.student = :student AND a.attendanceDate BETWEEN :startDate AND :endDate ORDER BY a.attendanceDate DESC, a.checkInTime DESC")
    List<Attendance> findByStudentAndAttendanceDateBetween(
            @Param("student") Student student,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}