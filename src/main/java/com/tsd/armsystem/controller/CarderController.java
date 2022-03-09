package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.Gender;
import com.tsd.armsystem.service.CarderService;
import com.tsd.armsystem.service.GenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/carder")
@AllArgsConstructor
public class CarderController {

    private final CarderService carderService;

    @PostMapping("/")
    public ResponseEntity<Carder> addCarder(@RequestBody Carder carder){
        Carder newCarder = carderService.addCarder(carder);
        return new ResponseEntity<>(newCarder, HttpStatus.CREATED);
    }
}
