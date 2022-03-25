package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CityRepository extends JpaRepository<City, Integer> {



        @Query(value = "Select * from city where idcity=?1",nativeQuery = true)
        City findbyCity(int cityid);

    Optional<City> findByIdcity(Integer id);

  List<City> findByName(String name);


}
