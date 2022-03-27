package com.tsd.armsystem.repository;


import com.tsd.armsystem.model.District;
import com.tsd.armsystem.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    List<District> findByProvince(Province province);


}
