package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.SchoolType;
import com.tsd.armsystem.service.SchoolTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/schoolType")
@AllArgsConstructor
public class SchoolTypeController {

    private final SchoolTypeService schoolTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<SchoolType>> getAllSchoolType(){
        List<SchoolType> allSchoolType = schoolTypeService.getAllSchoolType();
        return new ResponseEntity<>(allSchoolType, HttpStatus.OK);
    }

}
