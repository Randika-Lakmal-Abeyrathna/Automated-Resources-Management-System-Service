package com.tsd.armsystem.controller;

import com.tsd.armsystem.exception.GenderException;
import com.tsd.armsystem.model.Gender;
import com.tsd.armsystem.service.GenderService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/gender")
@AllArgsConstructor
public class GenderController {

    private final GenderService genderService;
// TODO- add auth to methods

    @GetMapping("/all")
    public ResponseEntity<List<Gender>> getAllGender(){
        List<Gender> allGender = genderService.getAllGender();
        return new ResponseEntity<>(allGender, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Gender> addGender(@RequestBody Gender gender){
        Gender newGender = genderService.addGender(gender);
        return new ResponseEntity<>(newGender,HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Gender> findGenderById(@PathVariable("id") Integer id){
        Gender gender = genderService.findGenderById(id);
        return new ResponseEntity<>(gender,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Gender> updateGender(@RequestBody Gender gender){
        Gender newGender  =genderService.updateGender(gender);
        return new ResponseEntity<>(newGender,HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<Gender> findGenderByGender(@PathVariable("name") String gender){
        Gender genderList = genderService.findGenderByGender(gender);
        return new ResponseEntity<>(genderList,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenderById(@PathVariable("id") Integer id){
        try{
            genderService.deleteGenderById(id);
        }catch(DataIntegrityViolationException e){
            throw new GenderException("The Gender you trying to delete already in Use");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
