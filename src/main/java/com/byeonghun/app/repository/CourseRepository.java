package com.byeonghun.app.repository;

import com.byeonghun.app.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
