package com.skpijtk.springboot_boilerplate.dto;

import java.util.List;

public class StudentDetailResponse {

    private Long studentId;
    private Long userId;
    private String studentName;
    private String nim;
    private String email;
    private List<?> attendanceData;

    public StudentDetailResponse(Long studentId, Long userId, String studentName, String nim, String email, List<?> attendanceData) {
        this.studentId = studentId;
        this.userId = userId;
        this.studentName = studentName;
        this.nim = nim;
        this.email = email;
        this.attendanceData = attendanceData;
    }

    public StudentDetailResponse() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<?> getAttendanceData() {
        return attendanceData;
    }

    public void setAttendanceData(List<?> attendanceData) {
        this.attendanceData = attendanceData;
    }
}