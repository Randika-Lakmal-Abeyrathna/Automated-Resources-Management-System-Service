package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.FormerExperiance;
import com.tsd.armsystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormerExperienceRepository extends JpaRepository<FormerExperiance, Integer> {

    List<FormerExperiance> findByTeacher(Teacher teacher);
}
