package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus,Integer> {

    List<MaritalStatus> findByStatus(String status);

    Optional<MaritalStatus> findById(Integer id);
}
