package com.davidoladeji.flexisaf.service.implementation;

import com.davidoladeji.flexisaf.exception.RecordNotFoundException;
import com.davidoladeji.flexisaf.model.Patient;
import com.davidoladeji.flexisaf.model.Staff;
import com.davidoladeji.flexisaf.repository.PatientRepository;
import com.davidoladeji.flexisaf.repository.StaffRepository;
import com.davidoladeji.flexisaf.service.PatientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements PatientService {

    PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getPatientsLessthanAge(int age) {
        return patientRepository.getByAgeLessThan(age);
    }
}