package com.tsd.armsystem.controller;


import com.tsd.armsystem.model.City;
import com.tsd.armsystem.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/city")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAllCity(){
        List<City> allCity = cityService.getAllCity();
        return new ResponseEntity<>(allCity, HttpStatus.OK);
    }
}
