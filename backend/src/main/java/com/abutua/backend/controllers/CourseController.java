package com.abutua.backend.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.backend.models.Course;

@RestController
public class CourseController {
    private List<Course> courses = Arrays.asList(
        new Course(1, "Java"),
        new Course(2, "Angular"),
        new Course(3, "AWS")
    );

    @GetMapping("courses")
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courses);
    }
}
