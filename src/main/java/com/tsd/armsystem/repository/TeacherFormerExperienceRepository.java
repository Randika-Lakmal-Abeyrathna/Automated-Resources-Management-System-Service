package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.FormerExperiance;
import com.tsd.armsystem.model.TeachersQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherFormerExperienceRepository extends JpaRepository<FormerExperiance,Integer> {

    Optional<FormerExperiance> findById(Integer id);

}
