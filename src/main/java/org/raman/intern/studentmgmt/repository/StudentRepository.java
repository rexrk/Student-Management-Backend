package org.raman.intern.studentmgmt.repository;

import org.raman.intern.studentmgmt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
