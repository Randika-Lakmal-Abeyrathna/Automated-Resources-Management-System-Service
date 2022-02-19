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
public class Carder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcarder;
    private int limit;
    private int current;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjects_id",referencedColumnName = "id")
    private Subjects subjects;
}
