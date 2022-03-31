package com.davidoladeji.ozeguru.repository;

import com.davidoladeji.ozeguru.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Long> , JpaSpecificationExecutor<Patient> {
    List<Patient> getByAgeLessThan(int age);
    List<Patient> getByCreatedAtBetween(Date fromDate, Date toDate);
}