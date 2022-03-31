package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Status;
import com.tsd.armsystem.model.UserType;
import com.tsd.armsystem.service.StatusService;
import com.tsd.armsystem.service.UserTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/status")
@AllArgsConstructor
public class StatusController {

    private final StatusService StatusService;

    @GetMapping("/all")
    public ResponseEntity<List<Status>> getAllUserTypes(){
        List<Status> allStatus = StatusService.getAllStatus();
        return new ResponseEntity<>(allStatus, HttpStatus.OK);
    }

}
