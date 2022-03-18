package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.ApproveUserUpdateDataRequest;
import com.tsd.armsystem.dto.RejectUserUpdateDataRequest;
import com.tsd.armsystem.dto.UserUpdateRequest;
import com.tsd.armsystem.exception.UpdateUserException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.UpdateUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UpdateUserService {

    private final UpdateUserRepository updateUserRepository;
    private final TeacherService teacherService;
    private final UserService userService;
    private final CityService cityService;
    private final GenderService genderService;
    private final MaritalStatusService maritalStatusService;
    private final SalutationService salutationService;


    public List<UpdateUser> getPendingApprovalUserUpdates(String nic){

        Teacher teacher =teacherService.getTeacherByUser(nic);

        if (!teacher.getTeacherType().getType().equalsIgnoreCase("principal")){
            throw new UpdateUserException("User is not an Principal");
        }

        School school = teacher.getSchool();
        List<Teacher> teacherList = teacherService.getTeachersBySchool(school.getIdschool());

        List<UpdateUser> updateUserList = new ArrayList<>();

        for (Teacher t : teacherList) {
            List<UpdateUser> list = updateUserRepository.findByUser(t.getUser());

            for (UpdateUser updateUser: list) {
                //status = 0 --> pending
                if (updateUser.getStatus() == 0){
                    updateUserList.add(updateUser);
                }
            }
        }

        return updateUserList;
    }

    public UpdateUser getUpdateUserById(Integer id){
        return updateUserRepository.findById(id).orElseThrow(()-> new UpdateUserException("Update record Not found"));
    }

    public void approveUserUpdate(Integer id){
        UpdateUser updateUser = getUpdateUserById(id);

        ApproveUserUpdateDataRequest request = new ApproveUserUpdateDataRequest();
        request.setFirstName(updateUser.getFirstName());
        request.setMiddleName(updateUser.getMiddleName());
        request.setLastName(updateUser.getLastName());
        request.setAddressNo(updateUser.getAddressNo());
        request.setAddressStreet1(updateUser.getAddressStreet());
        request.setAddressStreet2(updateUser.getAddressStreet2());
        request.setContactNumber1(updateUser.getContactNumber1());
        request.setContactNumber2(updateUser.getContactNumber2());
        request.setEmail(updateUser.getEmail());
        request.setCity(updateUser.getCity());
        request.setSalutation(updateUser.getSalutation());
        request.setGender(updateUser.getGender());
        request.setMaritalStatus(updateUser.getMaritalStatus());
        request.setUser(updateUser.getUser());

        User user = userService.updateUser(request);
        //Approve status -->1
        updateUser.setStatus(1);
        updateUser.setUpdated(true);

        updateUserRepository.save(updateUser);

    }

    public void rejectUserUpdate(RejectUserUpdateDataRequest rejectUserUpdateDataRequest){
        UpdateUser updateUser = getUpdateUserById(rejectUserUpdateDataRequest.getId());
        // Reject Status -->2
        updateUser.setStatus(2);
        updateUser.setComment(rejectUserUpdateDataRequest.getReason());

        updateUserRepository.save(updateUser);

    }

    public void addUpdateUser(UserUpdateRequest userUpdateRequest){
        UpdateUser updateUser = new UpdateUser();

        updateUser.setFirstName(userUpdateRequest.getFirstName());
        updateUser.setMiddleName(userUpdateRequest.getMiddleName());
        updateUser.setLastName(userUpdateRequest.getLastName());
        updateUser.setAddressNo(userUpdateRequest.getAddressNo());
        updateUser.setAddressStreet(userUpdateRequest.getAddressStreet1());
        updateUser.setAddressStreet2(userUpdateRequest.getAddressStreet2());
        updateUser.setContactNumber1(Integer.parseInt(userUpdateRequest.getContactNumber1()));
        updateUser.setContactNumber2(Integer.parseInt(userUpdateRequest.getContactNumber2()));
        updateUser.setEmail(userUpdateRequest.getEmail());
        updateUser.setUpdated(false);
        updateUser.setCreateddate(Instant.now());
        updateUser.setComment("");
        updateUser.setStatus(0);

        City city = cityService.getCityByCity(userUpdateRequest.getCity().toLowerCase());
        updateUser.setCity(city);

        Gender gender = genderService.findGenderByGender(userUpdateRequest.getGender().toLowerCase());
        updateUser.setGender(gender);

        MaritalStatus maritalStatus = maritalStatusService.getMaritalStatusByStatus(userUpdateRequest.getMaritalstatus().toLowerCase());
        updateUser.setMaritalStatus(maritalStatus);

        Salutation salutation = salutationService.getSalutationBySalutation(userUpdateRequest.getSalutation().toLowerCase());
        updateUser.setSalutation(salutation);

        User user = userService.getUserForTeacherByNIC(userUpdateRequest.getUserid());
        updateUser.setUser(user);

        updateUserRepository.save(updateUser);


    }


}
