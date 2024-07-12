package com.abutua.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.backend.models.Student;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<>();

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = students.stream().filter(s -> s.getId() == id).findFirst().orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found")
        );

        return ResponseEntity.ok(student);
    }
}
