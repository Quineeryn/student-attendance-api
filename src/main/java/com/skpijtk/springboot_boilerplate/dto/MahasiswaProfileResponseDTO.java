package com.skpijtk.springboot_boilerplate.dto;

public class MahasiswaProfileResponseDTO {

    private Long studentId;
    private String studentName;
    private String nim;

    private PageResponseDTO<AttendanceRecordDTO> attendanceData;

    public MahasiswaProfileResponseDTO(Long studentId, String studentName, String nim, PageResponseDTO<AttendanceRecordDTO> attendanceData) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.nim = nim;
        this.attendanceData = attendanceData;
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

    public PageResponseDTO<AttendanceRecordDTO> getAttendanceData() {
        return attendanceData;
    }

    public void setAttendanceData(PageResponseDTO<AttendanceRecordDTO> attendanceData) {
        this.attendanceData = attendanceData;
    }
}