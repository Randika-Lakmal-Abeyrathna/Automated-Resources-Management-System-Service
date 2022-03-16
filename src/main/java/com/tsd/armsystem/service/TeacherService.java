package com.tsd.armsystem.service;

import com.sun.source.tree.TryTree;
import com.tsd.armsystem.dto.SchoolRequest;
import com.tsd.armsystem.dto.TeacherRequest;
import com.tsd.armsystem.dto.UserResponse;
import com.tsd.armsystem.exception.TeacherException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;
    private final TeacherExperienceService teacherExperienceService;
    private final SchoolService schoolService;
    private final TeacherTypeService teacherTypeService;

    public Teacher getTeacherByUser(String nic) {
        User user = userService.getUserForTeacherByNIC(nic);
        Teacher teacher = teacherRepository.findByUser(user).orElseThrow(() -> new TeacherException("Teacher Not found with The given User "));
        return teacher;
    }

    public List<FormerExperiance> getTeacherExperienceByTeacherId(int id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherException("Teacher Not Found"));

        return teacherExperienceService.getExperienceByTeacher(teacher);
    }

    public List<Teacher> getTeachersBySchool(int SchoolId) {
        School school = schoolService.getSchoolById(SchoolId);
        return teacherRepository.findBySchool(school);

    }

    public Teacher addTeacher(TeacherRequest teacherRequest) {

        Teacher teacher = new Teacher();

        String appointmentDate = teacherRequest.getAppointmentDate();
        try {
            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(appointmentDate);
            teacher.setAppointmentdate(date1);
            String retireDate = teacherRequest.getRetireDate();
            teacher.setRetiredate(retireDate);
        } catch (Exception e) {
            throw new TeacherException("Invalid date format");
        }
        User user = userService.getUserForTeacherByNIC(teacherRequest.getUserNic());
        teacher.setUser(user);

        School school = schoolService.getSchoolById(teacherRequest.getSchoolId());
        teacher.setSchool(school);

        TeacherType teacherType = teacherTypeService.getTeacherTypeById(teacherRequest.getTeacherTypeId());
        teacher.setTeacherType(teacherType);


        return teacherRepository.save(teacher);
    }
}
