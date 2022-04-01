package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserRequest {

    private String nic;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressNo;
    private String addressStreet1;
    private String addressStreet2;
    private Integer contactNumber1;
    private Integer contactNumber2;
    private String email;
    private int userTypeId;
    private int statusId;
    private int salutationId;
    private int genderId;
    private int cityId;
    private int maritalStatusId;

}
