package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.Request;
import com.tsd.armsystem.model.RequestOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestOnboardingRepository extends JpaRepository<RequestOnboarding,Integer> {

    List<RequestOnboarding> findByCarderAndStatus(Carder carder,int status);

    Optional<RequestOnboarding> findById(Integer id);

    Optional<RequestOnboarding> findByRequest(Request request);

}
