package com.tsd.armsystem.service;

import com.tsd.armsystem.config.SecurityConfig;
import com.tsd.armsystem.dto.ForgotPasswordRequest;
import com.tsd.armsystem.dto.PasswordResetRequest;
import com.tsd.armsystem.dto.UserResponse;
import com.tsd.armsystem.exception.UserException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public void resetPassword(PasswordResetRequest passwordResetRequest){
        validatePassword(passwordResetRequest);

        User user = userRepository.findByNic(passwordResetRequest.getNic()).orElseThrow(() -> new UserException("User Not Found"));
        user.setPassword(passwordEncoder.encode(passwordResetRequest.getNewPassword1()));
        user.setLastmodifieddate(Instant.now());
        userRepository.save(user);


    }

    private void validatePassword(PasswordResetRequest passwordResetRequest){

        String p1 = passwordResetRequest.getNewPassword1();
        String p2 = passwordResetRequest.getNewPassword2();

        if (p1.equals(p2)){

        }else{
            throw new UserException("Password Mismatched ! ");
        }

    }

    public void forgetPassword(ForgotPasswordRequest ForgotPasswordRequest){
        User user = userRepository.findByNic(ForgotPasswordRequest.getNic()).orElseThrow(() -> new UserException("User Not Found"));

        String generatedPassword = SecurityConfig.randomPasswordGenerate();
        String email = user.getEmail();
        System.out.println("Generated Password"+generatedPassword);
        mailService.sendMail(new NotificationEmail(email,"New Password !","New Password for the system login is " + generatedPassword +"" +
                " Please reset the password after the login. Thank you."));
        user.setPassword(passwordEncoder.encode(generatedPassword));
        user.setLastmodifieddate(Instant.now());
        userRepository.save(user);
    }


    public UserResponse getUserByNIC(String nic){
       User user= userRepository.findByNic(nic).orElseThrow(() -> new UserException("User Not Found"));

       UserResponse userResponse = new UserResponse();

        userResponse.setNic(user.getNic());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setMiddleName(user.getMiddleName());
        userResponse.setLastName(user.getLastName());
        userResponse.setAddressNo(user.getAddressNo());
        userResponse.setAddressStreet(user.getAddressStreet());
        userResponse.setAddressStreet2(user.getAddressStreet2());
        userResponse.setContactNumber1(user.getContactNumber1());
        userResponse.setContactNumber2(user.getContactNumber2());
        userResponse.setEmail(user.getEmail());
        userResponse.setCity(user.getCity());
        userResponse.setGender(user.getGender());
        userResponse.setSalutation(user.getSalutation());
        userResponse.setStatus(user.getStatus());
        userResponse.setUserType(user.getUserType());
        userResponse.setImageData(user.getImageData());
        userResponse.setMaritalStatus(user.getMaritalStatus());

        return userResponse;


    }

}
