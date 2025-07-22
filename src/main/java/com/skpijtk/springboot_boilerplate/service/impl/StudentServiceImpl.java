package com.skpijtk.springboot_boilerplate.service.impl;

import com.skpijtk.springboot_boilerplate.dto.*;
import com.skpijtk.springboot_boilerplate.exception.DataIntegrityException;
import com.skpijtk.springboot_boilerplate.exception.EmailAlreadyExistsException;
import com.skpijtk.springboot_boilerplate.exception.NimAlreadyExistsException;
import com.skpijtk.springboot_boilerplate.exception.ResourceNotFoundException;
import com.skpijtk.springboot_boilerplate.model.Role;
import com.skpijtk.springboot_boilerplate.model.Student;
import com.skpijtk.springboot_boilerplate.model.User;
import com.skpijtk.springboot_boilerplate.repository.AttendanceRepository;
import com.skpijtk.springboot_boilerplate.repository.StudentRepository;
import com.skpijtk.springboot_boilerplate.repository.UserRepository;
import com.skpijtk.springboot_boilerplate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import com.skpijtk.springboot_boilerplate.repository.specification.StudentSpecification; // <-- IMPORT SPECIFICATION KITA
import org.springframework.data.domain.Page; // <-- IMPORT Page
import org.springframework.data.domain.Pageable; // <-- IMPORT Pageable
import org.springframework.data.jpa.domain.Specification; // <-- IMPORT Specification
import java.time.LocalDate; // <-- IMPORT LocalDate
import java.util.List; // <-- IMPORT List
import java.util.stream.Collectors; // <-- IMPORT Collectors

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AttendanceRepository attendanceRepository;


    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    @Transactional
    public StudentDetailResponse createStudent(StudentCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + request.getEmail() + "' sudah digunakan.");
        }
        if (studentRepository.existsByNim(request.getNim())) {
            throw new NimAlreadyExistsException("NIM '" + request.getNim() + "' sudah terdaftar.");
        }

        User newUser = new User();
        newUser.setName(request.getStudentName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.MAHASISWA);
        User savedUser = userRepository.save(newUser);

        Student newStudent = new Student(savedUser, request.getNim());
        Student savedStudent = studentRepository.save(newStudent);

        return mapToStudentDetailResponse(savedStudent);
    }

    @Override
    public StudentDetailResponse getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Mahasiswa dengan ID " + studentId + " tidak ditemukan."));
        return mapToStudentDetailResponse(student);
    }

    @Override
    @Transactional
    public StudentDetailResponse updateStudent(Long studentId, StudentEditRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Mahasiswa dengan ID " + studentId + " tidak ditemukan."));

        User user = student.getUser();

        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + request.getEmail() + "' sudah digunakan oleh user lain.");
        }
        if (!student.getNim().equals(request.getNim()) && studentRepository.existsByNim(request.getNim())) {
            throw new NimAlreadyExistsException("NIM '" + request.getNim() + "' sudah digunakan oleh mahasiswa lain.");
        }

        user.setName(request.getStudentName());
        user.setEmail(request.getEmail());
        student.setNim(request.getNim());

        Student updatedStudent = studentRepository.save(student);

        return mapToStudentDetailResponse(updatedStudent);
    }

    @Override
    @Transactional
    public StudentDeleteResponse deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Mahasiswa dengan ID " + studentId + " tidak ditemukan."));

        if (attendanceRepository.existsByStudent(student)) {
            throw new DataIntegrityException("Mahasiswa tidak bisa dihapus karena memiliki riwayat kehadiran. Hapus riwayat kehadiran terlebih dahulu.");
        }

        StudentDeleteResponse response = new StudentDeleteResponse(student.getId(), student.getUser().getName(), student.getNim());

        userRepository.delete(student.getUser());

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<StudentDetailResponse> getAllStudents(String studentName, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Specification<Student> spec = StudentSpecification.filterBy(studentName, startDate, endDate);

        Page<Student> studentPage = studentRepository.findAll(spec, pageable);

        List<StudentDetailResponse> studentDTOs = studentPage.getContent().stream()
                .map(this::mapToStudentDetailResponse)
                .collect(Collectors.toList());

        return new PageResponseDTO<>(
                studentDTOs,
                studentPage.getTotalElements(),
                studentPage.getTotalPages(),
                studentPage.getNumber(),
                studentPage.getSize()
        );
    }

    private StudentDetailResponse mapToStudentDetailResponse(Student student) {
        return new StudentDetailResponse(
                student.getId(),
                student.getUser().getId(),
                student.getUser().getName(),
                student.getNim(),
                student.getUser().getEmail(),
                Collections.emptyList()
        );
    }
}