package com.davidoladeji.flexisaf.controller;

import com.davidoladeji.flexisaf.exception.RecordNotFoundException;
import com.davidoladeji.flexisaf.exception.ResourceNotFoundException;
import com.davidoladeji.flexisaf.model.Patient;
import com.davidoladeji.flexisaf.model.Staff;
import com.davidoladeji.flexisaf.repository.StaffRepository;
import com.davidoladeji.flexisaf.service.PatientService;
import com.davidoladeji.flexisaf.service.StaffService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/staff/")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private PatientService patientService;


    /**
     * Gets all Staff
     */
    @GetMapping("all")
    public List<Staff> listStaff() {
        return staffService.listStaff();
    }

    /**
     * Updates a specific staff profile via Id // Requirement 2
     */
    @PutMapping("update/{Id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable long Id, @Valid @RequestBody Staff staffDetails) {
        Staff updatedStaff = staffService.updateStaff(Id, staffDetails);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);

    }
    /**
     * Get patients whose age is less than 2 years // Requirement 3
     */
    @GetMapping("patient/get/{age}")
    public  ResponseEntity<List<Patient>>  getPatientsLessthanAge(@PathVariable int age) {
        return new ResponseEntity<>(patientService.getPatientsLessthanAge(age), HttpStatus.OK);

    }

    /**
     * Delete a specific staff profile via Id
     */

    @DeleteMapping("delete/{Id}")
    public ResponseEntity<HttpStatus> deleteStaff(@PathVariable long Id) {
        staffService.deleteStaff(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}