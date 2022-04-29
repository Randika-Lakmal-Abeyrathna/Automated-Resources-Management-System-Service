package com.tsd.armsystem.controller;

import com.tsd.armsystem.dto.SchoolRequest;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.SchoolType;
import com.tsd.armsystem.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/school")
@AllArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/carder/{schoolId}")
    public ResponseEntity<List<Carder>> getSchoolCarderBySchoolId(@PathVariable Integer schoolId){
        List<Carder> carderList = schoolService.getSchoolCarderBySchoolId(schoolId);
        return new ResponseEntity<>(carderList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<School> addSchool(@RequestBody SchoolRequest schoolRequest){
        School school = schoolService.addSchool(schoolRequest);
        return new ResponseEntity<>(school, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<School>> getAllSchools(){
        List<School> allSchools = schoolService.getAllSchools();
        return new ResponseEntity<>(allSchools, HttpStatus.CREATED);
    }
    @GetMapping("/province/{provinceId}")
    public ResponseEntity<List<School>> getSchoolByProvince(@PathVariable Integer provinceId){
        List<School> schoolByProvince = schoolService.getSchoolByProvince(provinceId);
        return new ResponseEntity<>(schoolByProvince,HttpStatus.OK);
    }

}
