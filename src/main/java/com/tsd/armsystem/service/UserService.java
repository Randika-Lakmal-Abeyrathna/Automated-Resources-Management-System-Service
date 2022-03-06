package com.tsd.armsystem.service;

import com.tsd.armsystem.config.SecurityConfig;
import com.tsd.armsystem.dto.ForgotPasswordRequest;
import com.tsd.armsystem.dto.PasswordResetRequest;
import com.tsd.armsystem.exception.UserException;
import com.tsd.armsystem.model.User;
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
  //TODO -send the email to user

        user.setPassword(passwordEncoder.encode(generatedPassword));
        user.setLastmodifieddate(Instant.now());
        userRepository.save(user);
    }

}
