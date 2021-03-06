package com.tsd.armsystem.controller;

import com.tsd.armsystem.dto.*;
import com.tsd.armsystem.model.User;
import com.tsd.armsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest){
        userService.resetPassword(passwordResetRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        userService.forgetPassword(forgotPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{nic}")
    public ResponseEntity<UserResponse> getUserByNic(@PathVariable String nic){
        UserResponse user = userService.getUserByNIC(nic);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> newUserRegister(@RequestBody UserRequest request){
        User addUser = userService.addNewUser(request);
        return new ResponseEntity<>(addUser,HttpStatus.CREATED);
    }

    @GetMapping("/lock/{nic}")
    public ResponseEntity<?> lockUser(@PathVariable String nic){
        userService.lockUser(nic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/lock/all")
    public ResponseEntity<List<UserResponse>> getAllLockedUsers(){
        List<UserResponse> allLockUsers = userService.getAllLockUsers();

        return new ResponseEntity<>(allLockUsers,HttpStatus.OK);
    }

    @GetMapping("/unlock/{nic}")
    public ResponseEntity<?> unlockUser(@PathVariable String nic){
        userService.unlockUser(nic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/isLock/{nic}")
    public boolean isLocked(@PathVariable String nic){
        return userService.isUserLocked(nic);
    }

}
