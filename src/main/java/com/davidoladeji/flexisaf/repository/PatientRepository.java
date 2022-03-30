package com.davidoladeji.flexisaf.repository;

import com.davidoladeji.flexisaf.model.Patient;
import com.davidoladeji.flexisaf.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient, Long> , JpaSpecificationExecutor<Patient> {
    List<Patient> getByAgeLessThan(int age);
}