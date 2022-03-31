package com.davidoladeji.ozeguru.controller;

import com.davidoladeji.ozeguru.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TokenController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/token")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
        String token= staffService.login(username,password);
        if(StringUtils.isEmpty(token)){
            return "no token found";
        }
        return token;
    }
}