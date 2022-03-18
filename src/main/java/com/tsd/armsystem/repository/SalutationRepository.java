package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Salutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalutationRepository extends JpaRepository<Salutation,Integer> {
}
