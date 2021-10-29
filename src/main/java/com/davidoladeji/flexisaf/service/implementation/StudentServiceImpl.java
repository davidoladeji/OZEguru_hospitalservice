package com.davidoladeji.flexisaf.service.implementation;

import com.davidoladeji.flexisaf.exception.ResourceNotFoundException;
import com.davidoladeji.flexisaf.model.Student;
import com.davidoladeji.flexisaf.repository.StudentRepository;
import com.davidoladeji.flexisaf.service.StudentService;
import com.davidoladeji.flexisaf.util.GeneralUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {
    
    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {

        ResponseEntity<Student> resp = null;
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if(studentOptional.isPresent()){

          //  throw new IllegalStateException("A user with that matric number already exists");
        }else{
            Date today = new Date();
            //Convert today and the birthdate to Localdate
            LocalDate todayL = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dobL = student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = GeneralUtil.calculateAge(todayL, todayL);
            System.out.println("age: "+ age);
            //if(age >= 18 && age <= 25 ){
       /* }else{
            throw new IllegalStateException("User age must be greater than 18 and less than 25.");
        }*/
            student = studentRepository.save(student);
        }


        return student;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students = studentRepository.findAll();
        return students;
    }

    @Override
    public Student getStudent(long Id) {
        Student student = new Student();
        if(studentRepository.findById(Id).isPresent()){
            student = studentRepository.findById(Id).get();
        }
        return student;
    }

    @Override
    public void update(long Id, Student studentDetails) {
        if( studentRepository.findById(Id).isPresent()){
            Student updateStudent = studentRepository.findById(Id).get();
            updateStudent.setFirstName(studentDetails.getFirstName());
            updateStudent.setLastName(studentDetails.getLastName());
            updateStudent.setGender(studentDetails.getGender());
            studentRepository.save(updateStudent);
        }else{
            throw new IllegalStateException("A user with that id does not exist: "+Id);
        }

    }

    @Override
    public void delete(long Id) {
        if( studentRepository.findById(Id).isPresent()) {
            studentRepository.deleteById(Id);
        }else{
            throw new IllegalStateException("A user with that id does not exist: "+Id);
        }
    }
}