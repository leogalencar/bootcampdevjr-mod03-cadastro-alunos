package com.abutua.backend.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.backend.models.Student;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin
public class StudentController {
    private List<Student> students = new ArrayList<>();

    @PostConstruct
    private void initStudents() {
        students.add(new Student(1, "Alice Johnson", "alice.johnson@example.com", "(11) 91234-5678", 1, 2));
        students.add(new Student(2, "Bob Smith", "bob.smith@example.com", "(21) 97654-3210", 2, 3));
        students.add(new Student(3, "Carol Williams", "carol.williams@example.com", "(31) 99876-5432", 3, 1));
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = students.stream().filter(s -> s.getId() == id).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found"));

        return ResponseEntity.ok(student);
    }

    @PostMapping("students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        student.setId(students.size() + 1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        return ResponseEntity.created(location).body(student);
    }
}
