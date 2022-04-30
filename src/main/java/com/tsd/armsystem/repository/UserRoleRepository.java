package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles,Integer> {
}
