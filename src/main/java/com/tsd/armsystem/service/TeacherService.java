package com.tsd.armsystem.service;

import com.sun.source.tree.TryTree;
import com.tsd.armsystem.dto.*;
import com.tsd.armsystem.exception.TeacherException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.TeacherFormerExperienceRepository;
import com.tsd.armsystem.repository.TeacherQualificationRepository;
import com.tsd.armsystem.repository.TeacherRepository;
import com.tsd.armsystem.repository.TeacherSubjectRepository;
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
    private final SubjectService subjectService;
    private final TeacherExperienceService teacherExperienceService;
    private final SchoolService schoolService;
    private final TeacherTypeService teacherTypeService;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final TeacherQualificationRepository teacherQualificationRepository;
    private final TeacherFormerExperienceRepository teacherFormerExperienceRepository;

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

    public Teacher getTeacherById(int id) {
       return teacherRepository.findById(id).orElseThrow(()-> new TeacherException("Teacher Not Found"));
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

    public TeachersSubject addTeacherSubject(TeacherSubjectRequest teacherSubjectRequest) {

        TeachersSubject teachersSubject = new TeachersSubject();

        Teacher teacher = getTeacherById(teacherSubjectRequest.getTeacherId());
        teachersSubject.setTeacher(teacher);

        Subjects subjects = subjectService.getSubjectById(teacherSubjectRequest.getSubjectId());
        teachersSubject.setSubjects(subjects);

        return teacherSubjectRepository.save(teachersSubject);
    }

    public TeachersQualification addTeacherQualification (TeacherQualificationRequest teacherQualificationRequest) {

        TeachersQualification teachersQualification = new TeachersQualification();

        Teacher teacher = getTeacherById(teacherQualificationRequest.getTeacherId());
        teachersQualification.setTeacher(teacher);

        String qualification = teacherQualificationRequest.getQualification();
        teachersQualification.setQualification(qualification);

        return teacherQualificationRepository.save(teachersQualification);
    }

    public FormerExperiance addTeacherFormerExperience (TeacherFormerExperienceRequest teacherFormerExperienceRequest) {

        FormerExperiance formerExperiance = new FormerExperiance();

        try {
            String appointmentDate = teacherFormerExperienceRequest.getAppointmentDate();
            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(appointmentDate);
            formerExperiance.setAppointntdate(date1);

            String appointmentEndDate = teacherFormerExperienceRequest.getAppointmentEndDate();
            Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(appointmentEndDate);
            formerExperiance.setAppointmentenddate(date2);

        } catch (Exception e) {
            throw new TeacherException("Invalid date format");
        }

        School school = schoolService.getSchoolById(teacherFormerExperienceRequest.getSchoolId());
        formerExperiance.setSchool(school);

        Teacher teacher = getTeacherById(teacherFormerExperienceRequest.getTeacherId());
        formerExperiance.setTeacher(teacher);

        return teacherFormerExperienceRepository.save(formerExperiance);
    }
}
