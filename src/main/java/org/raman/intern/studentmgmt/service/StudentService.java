package org.raman.intern.studentmgmt.service;

import org.raman.intern.studentmgmt.entity.Student;
import org.raman.intern.studentmgmt.exception.EntityExistsException;
import org.raman.intern.studentmgmt.repository.StudentRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements UserDetailsService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByUsername(username);
        if (student.isEmpty()) {
            throw new UsernameNotFoundException("No student found with username: " + username);
        }

        return new User(
                student.get().getUsername(),
                student.get().getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + student.get().getRole().name().toString()))
        );
    }


    public Student addStudent(Student student) {
        if(studentRepository.findByUsername(student.getUsername()).isPresent()) {
            throw new EntityExistsException("Student already present");
        }
        return studentRepository.save(student);
    }

    public Optional<Student> getStudent(String id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> updateStudent(String id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setContactDetails(updatedStudent.getContactDetails());
                    student.setAddress(updatedStudent.getAddress());
                    student.setPinCode(updatedStudent.getPinCode());

                    return studentRepository.save(student);
                });
    }

    public boolean deleteStudent(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;

    }
}