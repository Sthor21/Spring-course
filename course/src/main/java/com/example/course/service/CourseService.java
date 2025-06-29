package com.example.course.service;

import com.example.course.beans.Course;
import com.example.course.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements CourseServiceInterface {

    @Autowired
    CourseRepo courseRepo;

    @Override
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        Optional<Course> option = courseRepo.findById(id);
        // ✅ Return null if not found - controller will throw CourseNotFoundException
        return option.orElse(null);
    }

    @Override
    public void addCourse(Course course) {
        // ✅ Simple save - controller already validates
        courseRepo.save(course);
    }

    @Override
    public boolean updateCourse(int id, Course course) {
        // ✅ Return false if course not found, true if updated successfully
        Optional<Course> option = courseRepo.findById(id);

        if (!option.isPresent()) {
            return false; // Course not found - controller will throw exception
        }

        // Update the course
        Course existingCourse = option.get();
        existingCourse.setName(course.getName());
        existingCourse.setDuration(course.getDuration());
        courseRepo.save(existingCourse);

        return true; // Success
    }

    @Override
    public boolean deleteCourse(int id) {
        // ✅ Check if course exists before deleting
        if (!courseRepo.existsById(id)) {
            return false; // Course not found - controller will throw exception
        }

        courseRepo.deleteById(id);
        return true; // Success
    }

    @Override
    public List<Course> filterMethod(int duration) {
        return courseRepo.filter(duration);
    }
}