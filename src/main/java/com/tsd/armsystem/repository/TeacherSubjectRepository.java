package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.model.TeachersSubject;
import com.tsd.armsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeachersSubject,Integer> {

    Optional<TeachersSubject> findById(Integer id);

}
