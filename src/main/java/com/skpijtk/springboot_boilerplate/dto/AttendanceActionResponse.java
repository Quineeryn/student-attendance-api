package com.skpijtk.springboot_boilerplate.dto; // Sesuaikan package

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceActionResponse {

    private Long studentId;
    private String studentName;
    private String nim;
    private Long attendanceId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkinTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutTime; // Akan null jika baru check-in

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate;

    private String notesCheckin;
    private String notesCheckout;
    private String statusCheckin;

    // Constructors
    public AttendanceActionResponse() {
    }

    public AttendanceActionResponse(Long studentId, String studentName, String nim, Long attendanceId,
                                    LocalDateTime checkinTime, LocalDateTime checkoutTime, LocalDate attendanceDate,
                                    String notesCheckin, String notesCheckout, String statusCheckin) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.nim = nim;
        this.attendanceId = attendanceId;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.attendanceDate = attendanceDate;
        this.notesCheckin = notesCheckin;
        this.notesCheckout = notesCheckout;
        this.statusCheckin = statusCheckin;
    }

    // Getters and Setters
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

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getNotesCheckin() {
        return notesCheckin;
    }

    public void setNotesCheckin(String notesCheckin) {
        this.notesCheckin = notesCheckin;
    }

    public String getNotesCheckout() {
        return notesCheckout;
    }

    public void setNotesCheckout(String notesCheckout) {
        this.notesCheckout = notesCheckout;
    }

    public String getStatusCheckin() {
        return statusCheckin;
    }

    public void setStatusCheckin(String statusCheckin) {
        this.statusCheckin = statusCheckin;
    }
}