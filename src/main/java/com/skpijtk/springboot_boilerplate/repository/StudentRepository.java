package com.skpijtk.springboot_boilerplate.repository;


import com.skpijtk.springboot_boilerplate.model.User;
import com.skpijtk.springboot_boilerplate.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Optional<Student> findByNim(String nim);
    boolean existsByNim(String nim);
    Optional<Student> findByUser(User user);
    boolean existsByUser(User user);
    Optional<Student> findByUserEmail(String email);

}