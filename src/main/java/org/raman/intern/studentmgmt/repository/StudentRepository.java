package org.raman.intern.studentmgmt.repository;

import org.raman.intern.studentmgmt.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
