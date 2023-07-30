package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.MaritalStatus;
import com.tsd.armsystem.service.MaritalStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/maritalstatus")
@AllArgsConstructor
public class MaritalStatusController {

    private final MaritalStatusService maritalStatusService;

    @GetMapping("/all")
    public ResponseEntity<List<MaritalStatus>> getAllMaritalStatus(){
        List<MaritalStatus> allMaritalStatus = maritalStatusService.getAllMaritalStatus();
        return new ResponseEntity<>(allMaritalStatus, HttpStatus.OK);
    }

}
