package com.example.course.controller;

import com.example.course.beans.Course;
import com.example.course.exception.CourseNotFoundException;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping()
    public List<Course> get(){
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getId(@PathVariable int id){
        Course course = courseService.getCourseById(id);
        if (course == null) {
            throw new CourseNotFoundException("Course with ID " + id + " not found in the database");
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody Course course){
        if (course == null) {
            throw new CourseNotFoundException("Course object cannot be null");
        }

        if (course.getName() == null || course.getName().trim().isEmpty()) {
            throw new CourseNotFoundException("Course name cannot be null or empty");
        }

        try {
            courseService.addCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully");
        } catch (Exception e) {
            throw new CourseNotFoundException("Failed to add course: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        boolean deleted = courseService.deleteCourse(id);
        if (!deleted) {
            throw new CourseNotFoundException("Course with ID " + id + " not found - cannot delete");
        }
        return ResponseEntity.ok("Course deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Course course){
        if (course == null) {
            throw new CourseNotFoundException("Course object cannot be null");
        }

        if (course.getName() == null || course.getName().trim().isEmpty()) {
            throw new CourseNotFoundException("Course name cannot be null or empty");
        }

        boolean updated = courseService.updateCourse(id, course);
        if (!updated) {
            throw new CourseNotFoundException("Course with ID " + id + " not found - cannot update");
        }
        return ResponseEntity.ok("Course updated successfully");
    }

    @GetMapping("/filter/{duration}")
    public ResponseEntity<List<Course>> filter( @PathVariable int duration){
        return ResponseEntity.ok(courseService.filterMethod(duration));
    }

    @GetMapping("/test-exception")
    public String testException() {
        throw new CourseNotFoundException("Test exception - this should be caught by global handler");
    }
}