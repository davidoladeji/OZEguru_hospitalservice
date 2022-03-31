package com.davidoladeji.ozeguru.service;


import com.davidoladeji.ozeguru.model.Staff;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    List<Staff> listStaff();

    String login(String username, String password);

    Optional<User> findByUuid(String uuid);

    Staff addStaff(Staff staff);

    Staff getStaff(long Id);

    Staff updateStaff(long Id, Staff staff);

    void deleteStaff(long Id);
}