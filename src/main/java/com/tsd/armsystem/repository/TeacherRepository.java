package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Request;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Optional<Teacher> findByUser(User user);

    Optional<Teacher> findById(Integer id);

    List<Teacher> findBySchool(School school);

    List<Teacher> findByUserNic(Teacher teacher);
}
