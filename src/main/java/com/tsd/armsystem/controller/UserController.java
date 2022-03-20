package com.tsd.armsystem.controller;

import com.tsd.armsystem.dto.ForgotPasswordRequest;
import com.tsd.armsystem.dto.PasswordResetRequest;
import com.tsd.armsystem.dto.UserResponse;
import com.tsd.armsystem.dto.Userdto;
import com.tsd.armsystem.model.User;
import com.tsd.armsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> newUserRegister(@RequestBody Userdto userRegister){
        User addUser = userService.addNewUser(userRegister);
        return new ResponseEntity<>(addUser,HttpStatus.CREATED);
    }

}
