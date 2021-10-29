package com.davidoladeji.flexisaf.controller;

import com.davidoladeji.flexisaf.exception.ResourceNotFoundException;
import com.davidoladeji.flexisaf.model.Student;
import com.davidoladeji.flexisaf.repository.StudentRepository;
import com.davidoladeji.flexisaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;



    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    @GetMapping("{Id}")
    public ResponseEntity<Student> getStudentById(@PathVariable  long Id){
        Student student = studentService.getStudent(Id);
        return ResponseEntity.ok(student);
    }


    @PutMapping("{Id}")
    public ResponseEntity<String> updateStudent(@PathVariable long Id,@RequestBody Student studentDetails) {
        studentService.update(Id, studentDetails);
        return new ResponseEntity<String>(studentService.getStudent(Id).toString(), HttpStatus.OK);
    }


    @DeleteMapping("{Id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long Id){
        studentService.delete(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}