package com.davidoladeji.ozeguru.service.implementation;

import com.davidoladeji.ozeguru.exception.RecordNotFoundException;
import com.davidoladeji.ozeguru.model.Patient;
import com.davidoladeji.ozeguru.model.Staff;
import com.davidoladeji.ozeguru.repository.PatientRepository;
import com.davidoladeji.ozeguru.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {

    PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }



    @Override
    public List<Patient> listPatient() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsLessthanAge(int age) {
        return patientRepository.getByAgeLessThan(age);
    }



    @Override
    public Patient getPatient(long Id) {
        if (patientRepository.findById(Id).isPresent()) {
            return patientRepository.findById(Id).get();
        }else {
            throw new RecordNotFoundException("Invalid patient id : " + Id);
        }
    }



    @Override
    public void deletePatientsBtwnDates(Date fromDate, Date toDate) {
        List<Patient> patients = patientRepository.getByCreatedAtBetween(fromDate, toDate);
        for(Patient patient : patients ){
            patientRepository.deleteById(patient.getId());
        }


    }
}