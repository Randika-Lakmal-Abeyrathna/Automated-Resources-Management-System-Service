package com.tsd.armsystem.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date appointmentdate;
//    TODO - should we keep the retire date. there is no exact date for this.
    private String retiredate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_nic",referencedColumnName = "nic")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_idschool",referencedColumnName = "idschool")
    private School school;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_type_id",referencedColumnName = "id")
    private TeacherType teacherType;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "teachers_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subjects_id")
    )
    private Set<Subjects> subjects = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "teachers_qualification",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<Qualification> qualifications = new HashSet<>();
}
