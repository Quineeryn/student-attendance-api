package com.skpijtk.springboot_boilerplate.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceRecordDTO {

    private Long attendanceId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkinTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate;

    private Boolean late;
    private String notesCheckin;
    private String notesCheckout;
    private String status;

    // Constructors
    public AttendanceRecordDTO() {
    }

    public AttendanceRecordDTO(Long attendanceId, LocalDateTime checkinTime, LocalDateTime checkoutTime,
                               LocalDate attendanceDate, Boolean late, String notesCheckin,
                               String notesCheckout, String status) {
        this.attendanceId = attendanceId;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.attendanceDate = attendanceDate;
        this.late = late;
        this.notesCheckin = notesCheckin;
        this.notesCheckout = notesCheckout;
        this.status = status;
    }


    // Getters and Setters
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

    public Boolean getLate() {
        return late;
    }

    public void setLate(Boolean late) {
        this.late = late;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}