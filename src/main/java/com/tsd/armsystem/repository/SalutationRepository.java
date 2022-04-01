package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Salutation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalutationRepository extends JpaRepository<Salutation,Integer> {

    List<Salutation> findBySalutation(String salutation);

    Optional<Salutation> findByIdsalutation(Integer id);
}
