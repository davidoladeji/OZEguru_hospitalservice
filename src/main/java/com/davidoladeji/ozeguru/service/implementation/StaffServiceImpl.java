package com.davidoladeji.ozeguru.service.implementation;

import com.davidoladeji.ozeguru.exception.RecordNotFoundException;
import com.davidoladeji.ozeguru.model.Staff;
import com.davidoladeji.ozeguru.repository.StaffRepository;
import com.davidoladeji.ozeguru.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StaffServiceImpl implements StaffService {

    StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }




    @Override
    public String login(String username, String password) {
        Optional<Staff> staff = staffRepository.login(username,password);
        if(staff.isPresent()){
            return staff.get().getUuid();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public Optional<User> findByUuid(String uuid) {
        Optional<Staff> staff = staffRepository.findByUuid(uuid);
        if(staff.isPresent()){
            Staff authstaff = staff.get();
            User user= new User(authstaff.getUsername(), authstaff.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }
    
    @Override
    public Staff addStaff(Staff staff) {
        staff = staffRepository.save(staff);
        return staff;
    }

    @Override
    public List<Staff> listStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaff(long Id) {
        if (staffRepository.findById(Id).isPresent()) {
            return staffRepository.findById(Id).get();
        }else {
            throw new RecordNotFoundException("Invalid staff id : " + Id);
        }
    }

    @Override
    public Staff updateStaff(long Id, Staff staffDetails) {
        if (staffRepository.findById(Id).isPresent()) {
            Staff updateStaff = staffRepository.findById(Id).get();
            updateStaff.setName(staffDetails.getName());
            updateStaff.setUsername(staffDetails.getUsername());
            updateStaff.setPassword(staffDetails.getPassword());
            return staffRepository.save(updateStaff);
        }else{
            throw new RecordNotFoundException("Invalid employee id : " + Id);
        }
    }

    @Override
    public void deleteStaff(long Id) {
        if (staffRepository.findById(Id).isPresent()) {
            staffRepository.deleteById(Id);
        } else {
            throw new IllegalStateException("A user with that id does not exist: " + Id);
        }
    }

}