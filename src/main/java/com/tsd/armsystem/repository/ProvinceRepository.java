package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Integer> {

    Optional<Province> findById(Integer id);
}
