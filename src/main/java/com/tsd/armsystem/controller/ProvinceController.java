package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/province")
@AllArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @GetMapping("/all")
    public ResponseEntity<List<Province>> getAllProvinces(){
        List<Province> allProvinces = provinceService.getAllProvinces();
        return new ResponseEntity<>(allProvinces, HttpStatus.CREATED);
    }

}
