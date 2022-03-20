package com.tsd.armsystem.service;

import com.tsd.armsystem.config.SecurityConfig;
import com.tsd.armsystem.controller.CityController;
import com.tsd.armsystem.dto.*;
import com.tsd.armsystem.exception.UserException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.CityRepository;
import com.tsd.armsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    public User getUserForTeacherByNIC(String nic){

       return  userRepository.findByNic(nic).orElseThrow(() -> new UserException("User Not Found"));
    }

    public User updateUser(ApproveUserUpdateDataRequest approveUserUpdateDataRequest){
        User  user = approveUserUpdateDataRequest.getUser();
        user.setFirstName(approveUserUpdateDataRequest.getFirstName());
        user.setMiddleName(approveUserUpdateDataRequest.getMiddleName());
        user.setLastName(approveUserUpdateDataRequest.getLastName());
        user.setAddressNo(approveUserUpdateDataRequest.getAddressNo());
        user.setAddressStreet(approveUserUpdateDataRequest.getAddressStreet1());
        user.setAddressStreet2(approveUserUpdateDataRequest.getAddressStreet2());
        user.setContactNumber1(approveUserUpdateDataRequest.getContactNumber1());
        user.setContactNumber2(approveUserUpdateDataRequest.getContactNumber2());
        user.setEmail(approveUserUpdateDataRequest.getEmail());
        user.setCity(approveUserUpdateDataRequest.getCity());
        user.setSalutation(approveUserUpdateDataRequest.getSalutation());
        user.setGender(approveUserUpdateDataRequest.getGender());
        user.setMaritalStatus(approveUserUpdateDataRequest.getMaritalStatus());
        user.setLastmodifieddate(Instant.now());

        return userRepository.save(user);
    }


    public User addNewUser(Userdto userRegister) {

        User reg = new User();

        reg.setAddressStreet(userRegister.getAddressStreet());
        reg.setAddressStreet2(userRegister.getAddressStreet2());

        reg.setContactNumber1(userRegister.getContactNumber1());
        reg.setContactNumber2(userRegister.getContactNumber2());
        reg.setCreateddate(userRegister.getCreateddate());
        reg.setEmail(userRegister.getEmail());
        reg.setFirstName(userRegister.getFirstName());
        reg.setGender(userRegister.getGender());
        reg.setImageData(userRegister.getImageData());
        reg.setLastmodifieddate(Instant.now());
        reg.setLastName(userRegister.getLastName());
        reg.setMaritalStatus(userRegister.getMaritalStatus());
        reg.setMiddleName(userRegister.getMiddleName());
        reg.setNic(userRegister.getNic());
        reg.setPassword(userRegister.getPassword());
        reg.setRoles(userRegister.getRoles());
        reg.setSalutation(userRegister.getSalutation());
        reg.setStatus(userRegister.getStatus());
        reg.setUserType(userRegister.getUserType());

        return userRepository.save(reg);
    }

    public void lockUser(String nic){
        User user = userRepository.findByNic(nic).orElseThrow(() -> new UserException("User Not Found"));
        user.setEnabled(false);
        userRepository.save(user);
    }

    public List<UserResponse> getAllLockUsers(){
        List<UserResponse> list = new ArrayList<>();

        List<User> users = userRepository.findByEnabled(false);

        for (User user: users) {
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

            list.add(userResponse);

        }

        return list;
    }

}
