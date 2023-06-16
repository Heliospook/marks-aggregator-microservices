package com.example.dataprovider.repositories;

import com.example.dataprovider.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
