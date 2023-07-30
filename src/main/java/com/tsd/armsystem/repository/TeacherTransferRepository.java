package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherTransferRepository extends JpaRepository<Request,Integer> {

    Optional<Request> findById(Integer id);

    Optional<Request> findByTeacher(Teacher teacher);

    List<Request> findByProvince(Province province);

    List<Request> findByTeacherAndStatus(Teacher teacher,Integer status);

}
