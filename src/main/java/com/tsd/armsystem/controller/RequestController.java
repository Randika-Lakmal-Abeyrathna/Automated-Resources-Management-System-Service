package com.tsd.armsystem.controller;


import com.tsd.armsystem.dto.RequestOnboardingRequest;
import com.tsd.armsystem.dto.RequestRejectRequest;
import com.tsd.armsystem.dto.UpdateRequest;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.Request;
import com.tsd.armsystem.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all/nonzonal")
    public ResponseEntity<List<Request>> getAllNonZonalRequests(){
        List<Request> allNonZonalRequest = requestService.getAllNonZonalRequest();
        return new ResponseEntity<>(allNonZonalRequest,HttpStatus.OK);
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

    @PostMapping("/approve")
    public ResponseEntity<?> approveTeacherRequest(@RequestBody RequestOnboardingRequest request){
        requestService.approveRequest(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reject")
    public ResponseEntity<?> rejectTeacherRequest(@RequestBody RequestRejectRequest requestRejectRequest){
        requestService.rejectRequest(requestRejectRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/user/{nic}")
    public ResponseEntity<List<Request>> getRequestByUserId(@PathVariable String nic){
        List<Request> pendingRequestByNic = requestService.getRequestByNic(nic);
        return new ResponseEntity<>(pendingRequestByNic,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRequest(@RequestBody UpdateRequest updateRequest){
        requestService.updateRequest(updateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
