package com.davidoladeji.flexisaf.bootstrap;

import com.davidoladeji.flexisaf.model.Staff;
import com.davidoladeji.flexisaf.repository.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

@Component
public class StaffLoader implements CommandLineRunner {

    private static final SecureRandom random = new SecureRandom();

    final String uuid = UUID.randomUUID().toString().replace("-", "");
    public final StaffRepository staffRepository;


    public StaffLoader(StaffRepository studentRepository) {
        this.staffRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createStaff();
    }
    private void createStaff() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
        formatter.parse("2001-10-29");
        if (staffRepository.count() == 0) {
            staffRepository.save(
                    Staff.builder()
                            .name("David Oladeji")
                            .username("daveola")
                            .password("mydream")
                            .build()
            );

            System.out.println("Staff default data autogenerated in database");
        }
    }
}