package com.tsd.armsystem.controller;

import com.tsd.armsystem.dto.*;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.service.TeacherTransferService;
import com.tsd.armsystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherTransferService teacherTransferService;

    @GetMapping("/find/{nic}")
    public ResponseEntity<Teacher> getTeacherByNic(@PathVariable String nic){
        Teacher teacher = teacherService.getTeacherByUser(nic);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/experience/{id}")
    public ResponseEntity<List<FormerExperiance>> getExperienceByTeacherId(@PathVariable int id){
        List<FormerExperiance> formerExperiances = teacherService.getTeacherExperienceByTeacherId(id);
        return new ResponseEntity<>(formerExperiances,HttpStatus.OK);
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<List<Teacher>> getTeachersBySchoolId(@PathVariable Integer id){
        List<Teacher> teachersBySchool = teacherService.getTeachersBySchool(id);
        return new ResponseEntity<>(teachersBySchool,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody TeacherRequest teacherRequest){
       return  teacherService.addTeacher(teacherRequest);
    }

    @PostMapping("/teacherSubject/add")
    public ResponseEntity<?> addTeacherSubject(@RequestBody TeacherSubjectRequest teacherSubjectRequest){
        TeachersSubject teachersSubject = teacherService.addTeacherSubject(teacherSubjectRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/teacherQualification/add")
    public ResponseEntity<?> addTeacherQualification(@RequestBody TeacherQualificationRequest teachersQualificationRequest){
        TeachersQualification teachersQualification = teacherService.addTeacherQualification(teachersQualificationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/teacherFormerExperience/add")
    public ResponseEntity<?> addTeacherFormerExperience(@RequestBody TeacherFormerExperienceRequest teacherFormerExperienceRequest){
        FormerExperiance formerExperiance = teacherService.addTeacherFormerExperience(teacherFormerExperienceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @PostMapping("/teacherTransfer/add")
    public ResponseEntity<?> addTeacherRequest(@RequestBody TeacherTransferRequest teacherTransferRequest){
        Request request = teacherTransferService.addTeacherRequest(teacherTransferRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        List<Teacher> allTeaches = teacherService.getAllTeaches();
        return new ResponseEntity<>(allTeaches,HttpStatus.OK);
    }

}
