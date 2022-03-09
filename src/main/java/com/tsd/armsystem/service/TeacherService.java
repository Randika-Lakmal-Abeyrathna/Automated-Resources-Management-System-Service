package com.tsd.armsystem.service;


import com.tsd.armsystem.exception.TeacherException;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.model.User;
import com.tsd.armsystem.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;

    public Teacher getTeacherByUser(String nic){
        User user = userService.getUserForTeacherByNIC(nic);
        Teacher teacher = teacherRepository.findByUser(user).orElseThrow(()-> new TeacherException("Teacher Not found with The given User "));
        return teacher;
    }


}
