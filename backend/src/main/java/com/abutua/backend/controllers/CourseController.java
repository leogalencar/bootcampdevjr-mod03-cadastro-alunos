package com.abutua.backend.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.abutua.backend.models.Course;

@RestController
public class CourseController {
    private List<Course> courses = Arrays.asList();
}
