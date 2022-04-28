package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeacherTransferData {
    private String nic;
    private String name;
    private Integer contactNo;
    private String requestType;
    private String requestStatus;
    private String comment;
    private String appointmentDate;
    private String onboardStatus;

}
