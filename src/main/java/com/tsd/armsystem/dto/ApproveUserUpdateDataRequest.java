package com.tsd.armsystem.dto;

import com.tsd.armsystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApproveUserUpdateDataRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String addressNo;
    private String addressStreet1;
    private String addressStreet2;
    private Integer contactNumber1;
    private Integer contactNumber2;
    private String email;
    private City city;
    private Salutation salutation;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private User user;

}
