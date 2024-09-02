package org.raman.intern.studentmgmt;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.raman.intern.studentmgmt.entity.Student;
import org.raman.intern.studentmgmt.repository.StudentRepository;
import org.raman.intern.studentmgmt.service.StudentService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student1;
    private Student student2;
    @BeforeEach
    void setStudent() {
        student1 = new Student("hash1", "rman", 9887896788L, "123 street", 123456);
        student2 = new Student("hash2", "kumar", 9897789098L, "543 street", 876543);
    }

    @Test
    void addStudentTest() {
        when(studentRepository.save(student1)).thenReturn(student1);
        Student result = studentService.addStudent(student1);
        assertEquals(student1, result);
    }

    @Test
    void getStudentTest() {
        when(studentRepository.findById("hash2")).thenReturn(Optional.of(student2));

        Optional<Student> result = studentService.getStudent("hash2");
        assertTrue(result.isPresent());
        assertEquals(student2, result.get());
    }

    @Test
    void getAllStudentsTest() {
        List<Student> students = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();
        assertEquals(2, result.size());
        assertTrue(result.contains(student1));
        assertTrue(result.contains(student2));
    }

    @Test
    void updateStudentTest() {
        Student updatedStudent = new Student("hash1", "rman updated", 1234567890L, "123 street", 654321);
        when(studentRepository.findById("hash1")).thenReturn(Optional.of(student1));
        when(studentRepository.save(student1)).thenReturn(updatedStudent);

        Optional<Student> result = studentService.updateStudent("hash1", updatedStudent);

        assertTrue(result.isPresent());
        assertEquals(updatedStudent.getName(), result.get().getName());
        assertEquals(updatedStudent.getPinCode(), result.get().getPinCode());

    }

    @Test
    void deleteStudentTest() {
        when(studentRepository.existsById("hash1")).thenReturn(true);
        boolean result = studentService.deleteStudent("hash1");
        assertTrue(result);
    }

    @Test
    void deleteStudentNotFound() {
        when(studentRepository.existsById("hash1")).thenReturn(false);
        boolean result = studentService.deleteStudent("hash1");
        assertFalse(result);
    }
}
