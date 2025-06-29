package com.example.course.service;

import com.example.course.beans.Course;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseServiceInterface {
    List<Course> getAll();
    Course getCourseById(int id);                    // Returns null if not found
    void addCourse(Course course);                   // Simple save
    boolean updateCourse(int id, Course course);     // Returns true/false
    boolean deleteCourse(int id);                    // Returns true/false
    List<Course> filterMethod(int duration);

}
