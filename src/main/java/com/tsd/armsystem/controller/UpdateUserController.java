package com.tsd.armsystem.controller;

import com.tsd.armsystem.dto.RejectUserUpdateDataRequest;
import com.tsd.armsystem.dto.UserUpdateRequest;
import com.tsd.armsystem.model.UpdateUser;
import com.tsd.armsystem.service.UpdateUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/updateuser")
@AllArgsConstructor
public class UpdateUserController {
    private final UpdateUserService updateUserService;

    @GetMapping("/find/{nic}")
    public ResponseEntity<List<UpdateUser>> getPendingUpdateUsersForPrincipal(@PathVariable String nic){
        List<UpdateUser> pendingApprovalUserUpdates = updateUserService.getPendingApprovalUserUpdates(nic);

        return new ResponseEntity<>(pendingApprovalUserUpdates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateUser> getUpdateUserById(@PathVariable Integer id){
        UpdateUser updateUser = updateUserService.getUpdateUserById(id);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<?> approveUpdateUserDetails(@PathVariable Integer id){
        updateUserService.approveUserUpdate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> rejectUpdateUserDetails(@RequestBody RejectUserUpdateDataRequest rejectUserUpdateDataRequest){
        updateUserService.rejectUserUpdate(rejectUserUpdateDataRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/request")
    public ResponseEntity<?> addUpdateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        updateUserService.addUpdateUser(userUpdateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
