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
public class School {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int idschool;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carder_idcarder",referencedColumnName = "idcarder")
    private Carder carder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_idcity",referencedColumnName = "idcity")
    private City city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zonal_idzonal",referencedColumnName = "idzonal")
    private Zonal zonal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_type_id",referencedColumnName = "id")
    private SchoolType schoolType;

}
