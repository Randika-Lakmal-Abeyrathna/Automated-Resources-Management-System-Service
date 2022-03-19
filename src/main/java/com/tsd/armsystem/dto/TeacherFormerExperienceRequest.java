package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeacherFormerExperienceRequest {

    private int teacherId;
    private int schoolId;
    private String appointmentDate;
    private String appointmentEndDate;
}
