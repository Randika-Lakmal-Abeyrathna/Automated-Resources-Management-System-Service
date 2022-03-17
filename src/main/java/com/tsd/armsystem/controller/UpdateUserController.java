package com.tsd.armsystem.controller;

import com.tsd.armsystem.model.UpdateUser;
import com.tsd.armsystem.service.UpdateUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
