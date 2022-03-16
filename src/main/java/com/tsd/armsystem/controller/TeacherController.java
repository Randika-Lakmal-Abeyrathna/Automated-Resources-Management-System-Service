package com.tsd.armsystem.controller;

import com.tsd.armsystem.dto.TeacherRequest;
import com.tsd.armsystem.model.FormerExperiance;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Teacher;
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
        Teacher teacher = teacherService.addTeacher(teacherRequest);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);

    }

}
