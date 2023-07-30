package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Zonal;
import com.tsd.armsystem.service.ZonalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/zonal")
@AllArgsConstructor
public class ZonalController {

    private final ZonalService zonalService;

    @GetMapping("/all")
    public ResponseEntity<List<Zonal>> getAllZonal(){
        List<Zonal> allZonal = zonalService.getAllZonal();
        return new ResponseEntity<>(allZonal, HttpStatus.OK);
    }
}
