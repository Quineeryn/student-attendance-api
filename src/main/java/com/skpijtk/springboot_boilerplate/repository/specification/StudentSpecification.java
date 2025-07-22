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

public class StudentSpecification {

    /**
     * Membuat objek Specification untuk memfilter Student.
     * @param studentName Nama mahasiswa (opsional).
     * @param startDate Tanggal mulai untuk filter kehadiran (opsional).
     * @param endDate Tanggal akhir untuk filter kehadiran (opsional).
     * @return Specification<Student> yang siap digunakan di repository.
     */
    public static Specification<Student> filterBy(String studentName, LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(studentName)) {
                Join<Student, User> userJoin = root.join("user");
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("name")), "%" + studentName.toLowerCase() + "%"));
            }

            if (startDate != null && endDate != null) {
                Join<Student, Attendance> attendanceJoin = root.join("attendances");
                predicates.add(criteriaBuilder.between(attendanceJoin.get("attendanceDate"), startDate, endDate));
                query.distinct(true);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}