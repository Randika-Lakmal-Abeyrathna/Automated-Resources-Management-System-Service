package com.tsd.armsystem.model;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

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
    private String password;
    private boolean enabled;
    private Date createddate;
    private String email;
    private Instant lastmodifieddate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_idcity",referencedColumnName = "idcity")
    private City city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_idgender",referencedColumnName = "idgender")
    private Gender gender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salutation_idsalutation",referencedColumnName = "idsalutation")
    private Salutation salutation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_idstatus",referencedColumnName = "idstatus")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_iduser_type",referencedColumnName = "iduser_type")
    private UserType userType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_data_idimage_data" ,referencedColumnName = "idimage_data")
    private ImageData imageData;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marital_status_id" ,referencedColumnName = "id")
    private MaritalStatus maritalStatus;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_nic"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


}
