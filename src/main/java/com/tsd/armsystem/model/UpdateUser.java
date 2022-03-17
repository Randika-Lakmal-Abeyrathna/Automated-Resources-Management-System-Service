package com.tsd.armsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;


@Data
@Entity(name = "update_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressNo;
    private String addressStreet;
    private String addressStreet2;
    private Integer contactNumber1;
    private Integer contactNumber2;
    private Instant createddate;
    private String email;
    private boolean updated;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_idcity",referencedColumnName = "idcity")
    private City city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marital_status_id" ,referencedColumnName = "id")
    private MaritalStatus maritalStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_idgender",referencedColumnName = "idgender")
    private Gender gender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salutation_idsalutation",referencedColumnName = "idsalutation")
    private Salutation salutation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_nic",referencedColumnName = "nic")
    private User user;
    private String comment;
    /**
     * pending ->0
     * approve ->1
     * reject ->2
     * **/
    private int status;


}
