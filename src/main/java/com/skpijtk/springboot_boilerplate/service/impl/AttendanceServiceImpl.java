package com.skpijtk.springboot_boilerplate.service.impl;

import com.skpijtk.springboot_boilerplate.dto.*;
import com.skpijtk.springboot_boilerplate.exception.ResourceNotFoundException;
import com.skpijtk.springboot_boilerplate.model.*;
import com.skpijtk.springboot_boilerplate.repository.AppSettingsRepository;
import com.skpijtk.springboot_boilerplate.repository.AttendanceRepository;
import com.skpijtk.springboot_boilerplate.repository.StudentRepository;
import com.skpijtk.springboot_boilerplate.repository.specification.AttendanceSpecification;
import com.skpijtk.springboot_boilerplate.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final AppSettingsRepository appSettingsRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, StudentRepository studentRepository, AppSettingsRepository appSettingsRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.appSettingsRepository = appSettingsRepository;
    }

    @Override
    @Transactional
    public AttendanceActionResponse performCheckIn(String userEmail, CheckInRequest request) {
        Student student = studentRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Data mahasiswa tidak ditemukan untuk email: " + userEmail));

        LocalDate today = LocalDate.now();

        if (attendanceRepository.existsByStudentAndAttendanceDate(student, today)) {
            throw new IllegalStateException("Anda sudah melakukan check-in hari ini.");
        }

        Appsettings settings = appSettingsRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Pengaturan aplikasi tidak ditemukan."));

        LocalDateTime checkInTime = LocalDateTime.now();
        LocalTime deadline = settings.getDefaultCheckInTime().plusMinutes(settings.getCheckInLateToleranceMinutes());

        CheckInStatus status = checkInTime.toLocalTime().isAfter(deadline)
                ? CheckInStatus.TERLAMBAT
                : CheckInStatus.TEPAT_WAKTU;

        Attendance newAttendance = new Attendance();
        newAttendance.setStudent(student);
        newAttendance.setAttendanceDate(today);
        newAttendance.setCheckInTime(checkInTime);
        newAttendance.setCheckInStatus(status);
        newAttendance.setCheckInNotes(request.getNotesCheckin());

        Attendance savedAttendance = attendanceRepository.save(newAttendance);
        return mapToAttendanceActionResponse(savedAttendance);
    }

    @Override
    @Transactional
    public AttendanceActionResponse performCheckOut(String userEmail, CheckOutRequest request) {
        Student student = studentRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Data mahasiswa tidak ditemukan untuk email: " + userEmail));

        LocalDate today = LocalDate.now();

        Attendance attendance = attendanceRepository.findByStudentAndAttendanceDate(student, today)
                .orElseThrow(() -> new IllegalStateException("Anda harus melakukan check-in terlebih dahulu sebelum check-out."));

        if (attendance.getCheckOutTime() != null) {
            throw new IllegalStateException("Anda sudah melakukan check-out hari ini.");
        }

        attendance.setCheckOutTime(LocalDateTime.now());
        attendance.setCheckOutNotes(request.getNotesCheckout());
        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return mapToAttendanceActionResponse(updatedAttendance);
    }

    @Override
    @Transactional(readOnly = true)
    public MahasiswaProfileResponseDTO getMahasiswaProfile(String userEmail, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Student student = studentRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Data mahasiswa tidak ditemukan untuk email: " + userEmail));

        Specification<Attendance> spec = AttendanceSpecification.filterBy(null, startDate, endDate, student.getId());
        Page<Attendance> attendancePage = attendanceRepository.findAll(spec, pageable);

        List<AttendanceRecordDTO> attendanceRecordDTOs = attendancePage.getContent().stream()
                .map(this::mapToAttendanceRecordDTO)
                .collect(Collectors.toList());

        PageResponseDTO<AttendanceRecordDTO> attendancePageDTO = new PageResponseDTO<>(
                attendanceRecordDTOs,
                attendancePage.getTotalElements(),
                attendancePage.getTotalPages(),
                attendancePage.getNumber(),
                attendancePage.getSize()
        );

        return new MahasiswaProfileResponseDTO(
                student.getId(),
                student.getUser().getName(),
                student.getNim(),
                attendancePageDTO
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<StudentAttendanceSummaryDTO> getFilteredAttendanceList(String studentName, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Specification<Attendance> spec = AttendanceSpecification.filterBy(studentName, startDate, endDate, null);
        Page<Attendance> attendancePage = attendanceRepository.findAll(spec, pageable);

        List<StudentAttendanceSummaryDTO> dtos = attendancePage.getContent().stream()
                .map(attendance -> new StudentAttendanceSummaryDTO(
                        attendance.getStudent().getId(),
                        attendance.getStudent().getUser().getName(),
                        attendance.getStudent().getNim(),
                        attendance.getId(),
                        attendance.getAttendanceDate(),
                        attendance.getCheckInTime(),
                        attendance.getCheckOutTime(),
                        attendance.getCheckInStatus() != null ? attendance.getCheckInStatus().name() : "BELUM_CHECKIN",
                        attendance.getCheckInNotes()
                ))
                .collect(Collectors.toList());

        return new PageResponseDTO<>(dtos, attendancePage.getTotalElements(), attendancePage.getTotalPages(), attendancePage.getNumber(), attendancePage.getSize());
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<AttendanceRecordDTO> getStudentAttendanceHistory(Long studentId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Mahasiswa dengan ID " + studentId + " tidak ditemukan.");
        }

        Specification<Attendance> spec = AttendanceSpecification.filterBy(null, startDate, endDate, studentId);
        Page<Attendance> attendancePage = attendanceRepository.findAll(spec, pageable);

        List<AttendanceRecordDTO> attendanceRecordDTOs = attendancePage.getContent().stream()
                .map(this::mapToAttendanceRecordDTO)
                .collect(Collectors.toList());

        return new PageResponseDTO<>(
                attendanceRecordDTOs,
                attendancePage.getTotalElements(),
                attendancePage.getTotalPages(),
                attendancePage.getNumber(),
                attendancePage.getSize()
        );
    }

    // Helper Methods
    private AttendanceActionResponse mapToAttendanceActionResponse(Attendance attendance) {
        Student student = attendance.getStudent();
        User user = student.getUser();
        return new AttendanceActionResponse(
                student.getId(),
                user.getName(),
                student.getNim(),
                attendance.getId(),
                attendance.getCheckInTime(),
                attendance.getCheckOutTime(),
                attendance.getAttendanceDate(),
                attendance.getCheckInNotes(),
                attendance.getCheckOutNotes(),
                attendance.getCheckInStatus() != null ? attendance.getCheckInStatus().name() : null
        );
    }

    private AttendanceRecordDTO mapToAttendanceRecordDTO(Attendance attendance) {
        return new AttendanceRecordDTO(
                attendance.getId(),
                attendance.getCheckInTime(),
                attendance.getCheckOutTime(),
                attendance.getAttendanceDate(),
                attendance.getCheckInStatus() == CheckInStatus.TERLAMBAT,
                attendance.getCheckInNotes(),
                attendance.getCheckOutNotes(),
                attendance.getCheckInStatus() != null ? attendance.getCheckInStatus().name() : "BELUM_CHECKIN"
        );
    }
}