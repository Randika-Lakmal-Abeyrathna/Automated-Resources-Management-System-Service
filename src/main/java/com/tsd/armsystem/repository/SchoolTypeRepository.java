package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.City;
import com.tsd.armsystem.model.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolTypeRepository extends JpaRepository<SchoolType,Integer> {

    Optional<SchoolType> findById(Integer id);
}
