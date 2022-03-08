package com.tsd.armsystem.dto;

import com.tsd.armsystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @Id
    private String nic;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressNo;
    private String addressStreet;
    private String addressStreet2;
    private Integer contactNumber1;
    private Integer contactNumber2;
    private String email;
    private City city;
    private Gender gender;
    private Salutation salutation;
    private Status status;
    private UserType userType;
    private ImageData imageData;
    private MaritalStatus maritalStatus;

}
