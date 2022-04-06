package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.model.Request;
import com.tsd.armsystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

    List<Request> findByTypeAndProvinceAndStatus(String Type, Province province,Integer status);

    List<Request> findByTypeAndStatus(String type,Integer status);

    Optional<Request> findById(Integer id);

    List<Request> findByTeacher(Teacher teacher);
}
