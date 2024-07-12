package com.abutua.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.abutua.backend.models.Student;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<>();
}
