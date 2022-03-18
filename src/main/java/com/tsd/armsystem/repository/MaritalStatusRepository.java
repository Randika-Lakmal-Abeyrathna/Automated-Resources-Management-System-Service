package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus,Integer> {

    List<MaritalStatus> findByStatus(String status);
}
