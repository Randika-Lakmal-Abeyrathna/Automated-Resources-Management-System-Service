package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.AdminProvince;
import com.tsd.armsystem.service.AdminProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminProvinceController {

    private final AdminProvinceService adminProvinceService;

    @GetMapping("/province/{nic}")
    public ResponseEntity<List<AdminProvince>> getAdminProvince(@PathVariable String nic){
        List<AdminProvince> list = adminProvinceService.getAdminProvinceByUser(nic);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
