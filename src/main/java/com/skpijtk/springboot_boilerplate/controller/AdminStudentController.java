package com.skpijtk.springboot_boilerplate.controller;

import com.skpijtk.springboot_boilerplate.dto.*;
import com.skpijtk.springboot_boilerplate.service.AttendanceService;
import com.skpijtk.springboot_boilerplate.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminStudentController {

    private final StudentService studentService;
    private final AttendanceService attendanceService;

    @Autowired
    public AdminStudentController(StudentService studentService, AttendanceService attendanceService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
    }

    // API #6: GET /admin/list_checkin_mahasiswa
    @GetMapping("/list_checkin_mahasiswa")
    public ResponseEntity<ApiResponse<PageResponseDTO<StudentAttendanceSummaryDTO>>> getCheckedInStudents(
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        PageResponseDTO<StudentAttendanceSummaryDTO> students = attendanceService.getFilteredAttendanceList(studentName, startDate, endDate, pageable);
        ApiResponse<PageResponseDTO<StudentAttendanceSummaryDTO>> response = new ApiResponse<>(
                students,
                "Daftar mahasiswa check-in berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #7: GET /admin/list_all_mahasiswa
    @GetMapping("/list_all_mahasiswa")
    public ResponseEntity<ApiResponse<PageResponseDTO<StudentDetailResponse>>> getAllStudents(
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        PageResponseDTO<StudentDetailResponse> students = studentService.getAllStudents(studentName, startDate, endDate, pageable);
        ApiResponse<PageResponseDTO<StudentDetailResponse>> response = new ApiResponse<>(
                students,
                "Daftar mahasiswa berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #8: DELETE /admin/mahasiswa/{id}
    @DeleteMapping("/mahasiswa/{id}")
    public ResponseEntity<ApiResponse<StudentDeleteResponse>> deleteStudent(@PathVariable("id") Long studentId) {
        StudentDeleteResponse deletedInfo = studentService.deleteStudent(studentId);
        ApiResponse<StudentDeleteResponse> response = new ApiResponse<>(
                deletedInfo,
                "Mahasiswa berhasil dihapus.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #9: GET /admin/mahasiswa/{id}
    @GetMapping("/mahasiswa/{id}")
    public ResponseEntity<ApiResponse<StudentDetailResponse>> getStudentById(@PathVariable("id") Long studentId) {
        StudentDetailResponse student = studentService.getStudentById(studentId);
        ApiResponse<StudentDetailResponse> response = new ApiResponse<>(
                student,
                "Data mahasiswa berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #10: GET /admin/list_attendance_mahasiswa
    @GetMapping("/list_attendance_mahasiswa")
    public ResponseEntity<ApiResponse<PageResponseDTO<AttendanceRecordDTO>>> getStudentAttendanceHistory(
            @RequestParam Long id_student,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 10, sort = "attendanceDate", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponseDTO<AttendanceRecordDTO> history = attendanceService.getStudentAttendanceHistory(id_student, startDate, endDate, pageable);
        ApiResponse<PageResponseDTO<AttendanceRecordDTO>> response = new ApiResponse<>(
                history,
                "Riwayat kehadiran berhasil diambil.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

    // API #11: POST /admin/add-mahasiswa
    @PostMapping("/add-mahasiswa")
    public ResponseEntity<ApiResponse<StudentDetailResponse>> createStudent(@Valid @RequestBody StudentCreationRequest request) {
        StudentDetailResponse createdStudent = studentService.createStudent(request);
        ApiResponse<StudentDetailResponse> response = new ApiResponse<>(
                createdStudent,
                "Mahasiswa berhasil dibuat.",
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API #12: PUT /admin/edit-mahasiswa/{id}
    @PutMapping("/edit-mahasiswa/{id}")
    public ResponseEntity<ApiResponse<StudentDetailResponse>> updateStudent(@PathVariable("id") Long studentId, @Valid @RequestBody StudentEditRequest request) {
        StudentDetailResponse updatedStudent = studentService.updateStudent(studentId, request);
        ApiResponse<StudentDetailResponse> response = new ApiResponse<>(
                updatedStudent,
                "Data mahasiswa berhasil diperbarui.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase()
        );
        return ResponseEntity.ok(response);
    }

}