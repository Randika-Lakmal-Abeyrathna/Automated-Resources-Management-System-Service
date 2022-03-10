package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {

    Optional<School> findByIdschool(Integer id);
}
