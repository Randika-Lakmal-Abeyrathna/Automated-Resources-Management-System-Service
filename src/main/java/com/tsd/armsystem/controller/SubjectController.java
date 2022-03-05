package com.tsd.armsystem.controller;


import com.tsd.armsystem.model.Subjects;
import com.tsd.armsystem.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/all")
    public ResponseEntity<List<Subjects>> getAllSubjects(){
        List<Subjects> allSubjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(allSubjects, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Subjects> addSubject(@RequestBody Subjects subjects){
        Subjects addedSubject = subjectService.addSubject(subjects);
        return new ResponseEntity<>(addedSubject,HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Subjects> getSubjectById(@PathVariable Integer id){
        Subjects subject = subjectService.getSubjectById(id);
        return new ResponseEntity<>(subject,HttpStatus.OK);
    }

}
