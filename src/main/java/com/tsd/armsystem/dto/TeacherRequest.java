package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeacherRequest {

    private String userNic;
    private int schoolId;
    private String appointmentDate;
    private String retireDate;
    private int teacherTypeId;

    private TeacherQualificationRequest teacherQualificationRequest;
    private List<TeacherSubjectRequest> teacherSubjectRequest;
    private List<TeacherFormerExperienceRequest> teacherFormerExperienceRequest;
}
