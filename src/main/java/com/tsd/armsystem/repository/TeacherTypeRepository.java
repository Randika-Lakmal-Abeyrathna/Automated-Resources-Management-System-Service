package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.TeacherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherTypeRepository extends JpaRepository<TeacherType,Integer> {

    Optional<TeacherType> findById(Integer id);
}
