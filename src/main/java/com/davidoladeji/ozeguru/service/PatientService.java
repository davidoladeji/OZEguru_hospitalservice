package com.davidoladeji.ozeguru.service;


import com.davidoladeji.ozeguru.model.Patient;
import com.davidoladeji.ozeguru.model.Staff;

import java.util.Date;
import java.util.List;

public interface PatientService {

    List<Patient> listPatient();
    List<Patient> getPatientsLessthanAge(int age);
    Patient getPatient(long Id);
    void deletePatientsBtwnDates(Date fromDate, Date toDate);

}