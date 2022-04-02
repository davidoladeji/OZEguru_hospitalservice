package com.davidoladeji.ozeguru.controller;

import com.davidoladeji.ozeguru.model.AuthenticationRequest;
import com.davidoladeji.ozeguru.model.Staff;
import com.davidoladeji.ozeguru.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/")
public class TokenController {

    @Autowired
    private StaffService staffService;

    @PostMapping(path = "token", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getToken(@RequestBody AuthenticationRequest authenticationRequest){
        String token= staffService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());

        if(StringUtils.isEmpty(token)){
            return  new ResponseEntity<>("no token found", HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("token", token));
      //  return new ResponseEntity<>(token, HttpStatus.OK);
    }
}