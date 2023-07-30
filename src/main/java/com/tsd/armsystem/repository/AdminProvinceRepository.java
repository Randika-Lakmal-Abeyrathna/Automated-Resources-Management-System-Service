package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.AdminProvince;
import com.tsd.armsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminProvinceRepository extends JpaRepository<AdminProvince,Integer> {

    @Query(value = "SELECT * FROM admin_province a where user_nic= ?1",nativeQuery = true)
    List<AdminProvince> findByUser(String nic);

}
