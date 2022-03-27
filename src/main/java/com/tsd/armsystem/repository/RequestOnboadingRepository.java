package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.RequestOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOnboadingRepository extends JpaRepository<RequestOnboarding,Integer> {
}
