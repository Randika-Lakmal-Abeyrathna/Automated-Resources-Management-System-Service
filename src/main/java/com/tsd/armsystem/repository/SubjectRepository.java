package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubjectRepository extends JpaRepository<Subjects,Integer> {

    Optional<Subjects> findById(Integer id);
}
