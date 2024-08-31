package org.raman.intern.studentmgmt;

import org.raman.intern.studentmgmt.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = StudentRepository.class)
public class StudentMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentMgmtApplication.class, args);
    }

}
