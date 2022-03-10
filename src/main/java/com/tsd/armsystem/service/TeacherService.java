package com.tsd.armsystem.service;


import com.tsd.armsystem.exception.TeacherException;
import com.tsd.armsystem.model.FormerExperiance;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.model.User;
import com.tsd.armsystem.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;
    private final TeacherExperienceService teacherExperienceService;
    private final SchoolService schoolService;

    public Teacher getTeacherByUser(String nic){
        User user = userService.getUserForTeacherByNIC(nic);
        Teacher teacher = teacherRepository.findByUser(user).orElseThrow(()-> new TeacherException("Teacher Not found with The given User "));
        return teacher;
    }

    public List<FormerExperiance> getTeacherExperienceByTeacherId(int id){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherException("Teacher Not Found"));

        return teacherExperienceService.getExperienceByTeacher(teacher);
    }

    public List<Teacher> getTeachersBySchool(int SchoolId){
        School school = schoolService.getSchoolById(SchoolId);
        return teacherRepository.findBySchool(school);

    }


}
