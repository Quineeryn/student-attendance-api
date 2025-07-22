package com.skpijtk.springboot_boilerplate.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class StudentAttendanceSummaryDTO {

    private Long studentId;
    private String studentName;
    private String nim;

    private Long attendanceId;
    private LocalDate attendanceDate;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
    private String status;
    private String notesCheckin;

    public StudentAttendanceSummaryDTO(Long studentId, String studentName, String nim, Long attendanceId, LocalDate attendanceDate, LocalDateTime checkinTime, LocalDateTime checkoutTime, String status, String notesCheckin) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.nim = nim;
        this.attendanceId = attendanceId;
        this.attendanceDate = attendanceDate;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.status = status;
        this.notesCheckin = notesCheckin;
    }

    public StudentAttendanceSummaryDTO() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

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

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotesCheckin() {
        return notesCheckin;
    }

    public void setNotesCheckin(String notesCheckin) {
        this.notesCheckin = notesCheckin;
    }
}