package com.tsd.armsystem.controller;

import com.tsd.armsystem.dto.CarderRequest;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.service.CarderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/carder")
@AllArgsConstructor
public class CarderController {

    private final CarderService carderService;

    @PostMapping("/add")
    public ResponseEntity<Carder> addCarder(@RequestBody CarderRequest carderRequest){
        Carder carder = carderService.addCarder(carderRequest);
        return new ResponseEntity<>(carder, HttpStatus.CREATED);

    }
}
