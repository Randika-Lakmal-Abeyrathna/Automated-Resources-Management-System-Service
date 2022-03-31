package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.UserType;
import com.tsd.armsystem.service.UserTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/userType")
@AllArgsConstructor
public class UserTypeController {

    private final UserTypeService userTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<UserType>> getAllUserTypes(){
        List<UserType> allUserTypes = userTypeService.getAllUserType();
        return new ResponseEntity<>(allUserTypes, HttpStatus.OK);
    }

}
