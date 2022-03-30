package com.davidoladeji.flexisaf.controller;

import com.davidoladeji.flexisaf.model.Staff;
import com.davidoladeji.flexisaf.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/public/")
public class PublicController {


    @Autowired
    private StaffService staffService;


    /**
     * Adds a new staff and assigns autogenerated UUID // Requirement 1
     */
    @PostMapping("staff/add")
    public ResponseEntity<Object> addStaff(@Valid @RequestBody Staff staff) {
        Staff createdStaff = staffService.addStaff(staff);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @GetMapping("staff/get/{Id}")
    public  ResponseEntity<Staff>  getStaffById(@PathVariable long Id) {
        return new ResponseEntity<>(staffService.getStaff(Id), HttpStatus.OK);

    }

}