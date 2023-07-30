package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.UserType;
import com.tsd.armsystem.model.Zonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Integer> {

    Optional<UserType> findByIduserType(Integer id);

}
