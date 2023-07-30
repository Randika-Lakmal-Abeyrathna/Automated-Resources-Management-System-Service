package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarderRepository extends JpaRepository<Carder,Integer> {

    List<Carder> findCarderBySchool(School school);

    List<Carder> findBySubjects(Subjects subjects);

    Optional<Carder> findByIdcarder(Integer id);

}
