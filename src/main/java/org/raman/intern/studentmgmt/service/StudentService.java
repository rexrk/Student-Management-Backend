package org.raman.intern.studentmgmt.service;
import org.raman.intern.studentmgmt.entity.Student;
import org.raman.intern.studentmgmt.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setContactDetails(updatedStudent.getContactDetails());
                    student.setAddress(updatedStudent.getAddress());
                    student.setPinCode(updatedStudent.getPinCode());

                    return studentRepository.save(student);
                });
    }

    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}