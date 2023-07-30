package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Qualification;
import com.tsd.armsystem.service.QualificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qualification")
@AllArgsConstructor
public class QualificationController {

    private final QualificationService qualificationService;

    @GetMapping("/all")
    public ResponseEntity<List<Qualification>> getAllQualification(){
        List<Qualification> allQualification = qualificationService.getAllQualification();
        return new ResponseEntity<>(allQualification, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Qualification> addQualification(@RequestBody Qualification qualification){
        Qualification addQualification = qualificationService.addQualification(qualification);
        return new ResponseEntity<>(addQualification,HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Qualification> findById(@PathVariable Integer id){
        Qualification qualification = qualificationService.findById(id);
        return new ResponseEntity<>(qualification,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Qualification> updateQualification(@RequestBody Qualification qualification){
        Qualification updatedQualification = qualificationService.updateQualification(qualification);
        return new ResponseEntity<>(updatedQualification,HttpStatus.OK);
    }
}
