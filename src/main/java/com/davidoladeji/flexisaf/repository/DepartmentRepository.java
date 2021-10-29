package com.davidoladeji.flexisaf.repository;

import com.davidoladeji.flexisaf.model.Department;
import com.davidoladeji.flexisaf.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

}