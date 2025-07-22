package com.skpijtk.springboot_boilerplate.dto;

public class StudentDeleteResponse {

    private Long studentId;
    private String studentName;
    private String nim;

    // Constructor
    public StudentDeleteResponse(Long studentId, String studentName, String nim) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.nim = nim;
    }


    public StudentDeleteResponse() {
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
}