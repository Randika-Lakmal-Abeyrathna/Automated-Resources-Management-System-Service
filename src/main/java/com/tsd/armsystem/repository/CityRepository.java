package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    Optional<City> findByIdcity(Integer id);

    List<City> findByName(String name);
}
