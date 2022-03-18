package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus,Integer> {
}
