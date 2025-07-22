package com.skpijtk.springboot_boilerplate.repository.specification;

import com.skpijtk.springboot_boilerplate.model.Attendance;
import com.skpijtk.springboot_boilerplate.model.Student;
import com.skpijtk.springboot_boilerplate.model.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceSpecification {

    /**
     * Membuat query dinamis untuk entitas Attendance.
     * @param studentName Nama mahasiswa untuk difilter (opsional).
     * @param startDate Tanggal mulai filter (opsional).
     * @param endDate Tanggal akhir filter (opsional).
     * @param studentId ID mahasiswa spesifik untuk difilter (opsional).
     * @return Specification untuk Attendance.
     */
    public static Specification<Attendance> filterBy(String studentName, LocalDate startDate, LocalDate endDate, Long studentId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Attendance, Student> studentJoin = root.join("student");

            if (StringUtils.hasText(studentName)) {
                Join<Student, User> userJoin = studentJoin.join("user");
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("name")), "%" + studentName.toLowerCase() + "%"));
            }

            if (studentId != null) {
                predicates.add(criteriaBuilder.equal(studentJoin.get("id"), studentId));
            }

            if (startDate != null && endDate != null) {
                predicates.add(criteriaBuilder.between(root.get("attendanceDate"), startDate, endDate));
            } else if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("attendanceDate"), startDate));
            } else if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("attendanceDate"), endDate));
            }

            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}