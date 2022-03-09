package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarderRepository extends JpaRepository<Carder,Integer> {


}
