package com.davidoladeji.flexisaf.service;


import com.davidoladeji.flexisaf.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getPatientsLessthanAge(int age);

}