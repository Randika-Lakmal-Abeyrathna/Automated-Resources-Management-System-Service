package com.tsd.armsystem.service;

import com.tsd.armsystem.config.SecurityConfig;
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
    private final UserTypeService userTypeService;
    private final StatusService statusService;
    private final SalutationService salutationService;
    private final GenderService genderService;
    private final CityService cityService;
    private final MaritalStatusService maritalStatusService;


    private CityRepository cityRepository;

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


    public User addNewUser(UserRequest request) {
        System.out.println("Request Data => " + request);
        User user = new User();

        if (userRepository.countByNic(request.getNic()) >0 ){
            throw new UserException("User Already Exists ");
        }

        user.setNic(request.getNic());
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setAddressNo(request.getAddressNo());
        user.setAddressStreet(request.getAddressStreet1());
        user.setAddressStreet2(request.getAddressStreet2());
        user.setContactNumber1(request.getContactNumber1());
        user.setContactNumber2(request.getContactNumber2());
        user.setEmail(request.getEmail());

        user.setEnabled(true);

        UserType userType = userTypeService.getUserTypeBuId(request.getUserTypeId());
        user.setUserType(userType);

        Status status = statusService.getStatusById(request.getStatusId());
        user.setStatus(status);

        Salutation salutation =  salutationService.getSalutationById(request.getSalutationId());
        user.setSalutation(salutation);

        Gender gender = genderService.findGenderById(request.getGenderId());
        user.setGender(gender);

        City city = cityService.getCityById(request.getCityId());
        user.setCity(city);

        MaritalStatus maritalStatus = maritalStatusService.getMaritalStatusById(request.getMaritalStatusId());
        user.setMaritalStatus(maritalStatus);

        String generatedPassword = SecurityConfig.randomPasswordGenerate();
        user.setPassword(passwordEncoder.encode(generatedPassword));

        user.setCreateddate(Instant.now());
        user.setLastmodifieddate(Instant.now());

        User saveUser = userRepository.save(user);

        mailService.sendMail(new NotificationEmail(saveUser.getEmail(),"User Registered !","New Password for the system login is " + generatedPassword +"" +
                " Please reset the password after the login. Thank you."));

        return saveUser;
    }

    public void lockUser(String nic){
        User user = userRepository.findByNic(nic).orElseThrow(() -> new UserException("User Not Found"));
        user.setEnabled(false);
        user.setLastmodifieddate(Instant.now());
        userRepository.save(user);
    }

    public void unlockUser(String nic){
        User user = userRepository.findByNic(nic).orElseThrow(() -> new UserException("User Not Found"));
        user.setEnabled(true);
        user.setLastmodifieddate(Instant.now());
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

    public boolean isUserLocked(String nic){
        User user = userRepository.findByNic(nic).orElseThrow(() -> new UserException("User Not Found"));
        return user.isEnabled();
    }

}
