package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {

    Optional<Status> findByIdstatus(Integer id);

}
