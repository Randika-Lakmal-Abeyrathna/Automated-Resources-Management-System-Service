package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {

    Optional<Permission> findByName(String permissionName);

    int countByName(String permissionName);
}
