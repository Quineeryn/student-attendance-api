package com.skpijtk.springboot_boilerplate.service.impl;

import com.skpijtk.springboot_boilerplate.dto.ResumeCheckinResponseDTO;
import com.skpijtk.springboot_boilerplate.dto.TotalMahasiswaResponseDTO;
import com.skpijtk.springboot_boilerplate.model.CheckInStatus;
import com.skpijtk.springboot_boilerplate.repository.AttendanceRepository;
import com.skpijtk.springboot_boilerplate.repository.StudentRepository;
import com.skpijtk.springboot_boilerplate.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public DashboardServiceImpl(StudentRepository studentRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    @Transactional(readOnly = true) // Transaksi read-only, baik untuk performa query GET
    public TotalMahasiswaResponseDTO getTotalMahasiswa() {
        long total = studentRepository.count();
        return new TotalMahasiswaResponseDTO(total);
    }

    @Override
    @Transactional(readOnly = true)
    public ResumeCheckinResponseDTO getCheckinResume() {
        LocalDate today = LocalDate.now();

        long totalMahasiswa = studentRepository.count();

        long totalCheckin = attendanceRepository.countByAttendanceDate(today);

        long totalTelatCheckin = attendanceRepository.countByAttendanceDateAndCheckInStatus(today, CheckInStatus.TERLAMBAT);

        long totalBelumCheckin = totalMahasiswa - totalCheckin;

        return new ResumeCheckinResponseDTO(
                totalMahasiswa,
                totalCheckin,
                totalBelumCheckin,
                totalTelatCheckin
        );
    }
}