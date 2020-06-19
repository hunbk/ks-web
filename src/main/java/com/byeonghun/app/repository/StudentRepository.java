package com.byeonghun.app.repository;

import com.byeonghun.app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(final String name);
    Student findByEmail(final String email);
    List<Student> findAllByEmailIsLike(final String email);
}
