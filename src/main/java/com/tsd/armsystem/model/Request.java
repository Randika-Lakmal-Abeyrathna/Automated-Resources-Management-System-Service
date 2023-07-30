package com.tsd.armsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrequest;
    private String type;
    private int status;
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_idschool",referencedColumnName = "idschool")
    private School school;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id",referencedColumnName = "id")
    private Province province;

}
