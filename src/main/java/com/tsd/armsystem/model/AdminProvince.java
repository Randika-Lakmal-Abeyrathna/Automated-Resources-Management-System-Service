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
public class AdminProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_nic",referencedColumnName = "nic")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id",referencedColumnName = "id")
    private Province province;
}
