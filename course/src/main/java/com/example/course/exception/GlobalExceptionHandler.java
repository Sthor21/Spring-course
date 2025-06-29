package com.example.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourseNotFoundException(CourseNotFoundException e)
    {
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
