package com.tsd.armsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private String addressNo;
    private String addressStreet1;
    private String addressStreet2;
    private String city;
    private String contactNumber1;
    private String contactNumber2;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String maritalstatus;
    private String salutation;
    private String userid;


}
