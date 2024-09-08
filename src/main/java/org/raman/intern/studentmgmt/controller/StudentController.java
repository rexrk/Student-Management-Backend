package org.raman.intern.studentmgmt.controller;

import jakarta.validation.Valid;
import org.raman.intern.studentmgmt.entity.Student;
import org.raman.intern.studentmgmt.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{username}/{id}")
    @PreAuthorize("#username.equals(authentication.name)")
    @Secured("ROLE_STUDENT")
    public ResponseEntity<Student> getStudentInfo(@PathVariable String username, @PathVariable String id, Authentication authentication) {
        return studentService.getStudent(username, id)
                .filter(student -> student.getUsername().equals(authentication.getName()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{username}/{id}")
    @PreAuthorize("#username.equals(authentication.name)")
    @Secured("ROLE_STUDENT")
    public ResponseEntity<Object> deleteStudent(@PathVariable String username, @PathVariable String id) {
        return Optional.of(studentService.deleteStudent(username, id))
                .filter(deleted -> deleted)
                .map(deleted -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{username}/{id}")
    @PreAuthorize("#username.equals(authentication.name)")
    @Secured("ROLE_STUDENT")
    public ResponseEntity<Student> updateStudent(@PathVariable String username,
                                                 @PathVariable String id, @RequestBody @Valid Student updatedStudent) {
        return studentService.updateStudent(username, id, updatedStudent)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}