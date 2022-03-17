package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.UpdateUserException;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.model.UpdateUser;
import com.tsd.armsystem.model.User;
import com.tsd.armsystem.repository.UpdateUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UpdateUserService {

    private final UpdateUserRepository updateUserRepository;
    private final TeacherService teacherService;


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


}
