package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeacherRequest {

    private String userNic;
    private int schoolId;
    private String appointmentDate;
    private String retireDate;
    private int teacherTypeId;
}
