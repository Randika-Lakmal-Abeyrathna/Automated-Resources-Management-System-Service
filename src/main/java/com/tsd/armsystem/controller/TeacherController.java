package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.FormerExperiance;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
