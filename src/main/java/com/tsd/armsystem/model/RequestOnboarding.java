package com.tsd.armsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestOnboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int status;
    private Date appointmentDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_idrequest", referencedColumnName = "idrequest")
    private Request request;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carder_idcarder",referencedColumnName = "idcarder")
    private Carder carder;

}
