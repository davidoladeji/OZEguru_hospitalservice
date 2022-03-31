package com.davidoladeji.ozeguru.repository;

import com.davidoladeji.ozeguru.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface StaffRepository extends JpaRepository<Staff, Long> , JpaSpecificationExecutor<Staff> {

    @Query(value = "SELECT u FROM Staff u where u.username = ?1 and u.password = ?2 ")
    Optional<Staff> login(String username,String password);

    Optional<Staff> findByUuid(String token);
}