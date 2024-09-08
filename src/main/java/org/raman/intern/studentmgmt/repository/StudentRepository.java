package org.raman.intern.studentmgmt.repository;

import org.raman.intern.studentmgmt.entity.Student;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findByUsername(String username);
}
