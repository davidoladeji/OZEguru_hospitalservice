package com.davidoladeji.flexisaf.repository;

import com.davidoladeji.flexisaf.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByMatricNumber(String matricNumber);
    Student getStudentByMatricNumber(String matricNumber);
}