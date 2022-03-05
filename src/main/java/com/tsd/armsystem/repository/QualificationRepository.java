package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Integer> {

    Optional<Qualification> findById(Integer id);

    int countByName(String name);

    int countByDescription(String description);
}
