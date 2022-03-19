package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeacherQualificationRequest {

    private int teacherId;
    private String qualification;
}
