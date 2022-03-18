package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.Salutation;
import com.tsd.armsystem.service.SalutationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/salutation")
@AllArgsConstructor
public class SalutationController {

    private final SalutationService salutationService;

    @GetMapping("/all")
    public ResponseEntity<List<Salutation>> getAllSalutation(){
        List<Salutation> allSalutation = salutationService.getAllSalutation();
        return new ResponseEntity<>(allSalutation, HttpStatus.OK);
    }
}
