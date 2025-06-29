package com.example.course.repository;

import com.example.course.beans.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
    @Query(value = "select * from Course c where c.duration > :threshold",nativeQuery = true)
    List<Course> filter(@Param("threshold")int duration);
}
