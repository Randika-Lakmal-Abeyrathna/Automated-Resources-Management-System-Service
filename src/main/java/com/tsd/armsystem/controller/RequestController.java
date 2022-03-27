package com.tsd.armsystem.controller;


import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.Request;
import com.tsd.armsystem.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@AllArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Request>> getAllZonalRequests(@PathVariable Integer id){
        List<Request> allZonalRequest = requestService.getAllZonalRequest(id);

        return new ResponseEntity<>(allZonalRequest,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer id){
        Request requestById = requestService.getRequestById(id);
        return new ResponseEntity<>(requestById,HttpStatus.OK);
    }

    @GetMapping("/suggest/{id}")
    public ResponseEntity<List<Carder>> getSelectedSchoolsForRequestId(@PathVariable Integer id){
        List<Carder> suggestedSchoolsByRequestId = requestService.getSuggestedSchoolsByRequestId(id);
        return new ResponseEntity<>(suggestedSchoolsByRequestId,HttpStatus.OK);
    }

}
