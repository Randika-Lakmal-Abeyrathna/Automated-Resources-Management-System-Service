package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
