package com.tsd.armsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcity;
    private String name;
    private String postalcode;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id",referencedColumnName = "id")
    private District district;


}
