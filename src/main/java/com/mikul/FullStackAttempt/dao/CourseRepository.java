package com.mikul.FullStackAttempt.dao;

import com.mikul.FullStackAttempt.dto.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
