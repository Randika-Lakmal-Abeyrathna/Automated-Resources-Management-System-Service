package com.tsd.armsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity(name="formerexperiance")
@NoArgsConstructor
@AllArgsConstructor
public class FormerExperiance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idformerexperiance;
    private Date appointntdate;
    private Date appointmentenddate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_idschool",referencedColumnName = "idschool")
    private School school;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private Teacher teacher;
}
