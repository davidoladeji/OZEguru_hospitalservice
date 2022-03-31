package com.davidoladeji.ozeguru.controller;

import com.davidoladeji.ozeguru.model.Patient;
import com.davidoladeji.ozeguru.model.Staff;
import com.davidoladeji.ozeguru.service.PatientService;
import com.davidoladeji.ozeguru.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
     * Gets all Staff
     */
    @GetMapping("patients/all")
    public List<Patient> listPatient() {
        return patientService.listPatient();
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
     * Export specific Patient Data to CSV // Requirement 4
     */

    @GetMapping("/patient/export/{Id}")
    public void exportPatientDataToCSV(@PathVariable long Id, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        Patient patient = patientService.getPatient(Id);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"S\\N", "Name", "Age", "Last Visit", "CreatedAt"};
        String[] nameMapping = {"id", "name", "age", "last_visit_date", "created_at"};

        csvWriter.writeHeader(csvHeader);

        csvWriter.write(patient, nameMapping);

        csvWriter.close();

    }

    /**
     * Delete patients date of creation are is between a date range // Requirement 5
     */
    @DeleteMapping("patient/delete")
    public  ResponseEntity<HttpStatus>  detePatientsBetweenRange(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
        patientService.deletePatientsBtwnDates(fromDate, toDate);
        return new ResponseEntity<>(HttpStatus.OK);

    }



    @GetMapping("staff/get/{Id}")
    public  ResponseEntity<Staff>  getStaffById(@PathVariable long Id) {
        return new ResponseEntity<>(staffService.getStaff(Id), HttpStatus.OK);
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