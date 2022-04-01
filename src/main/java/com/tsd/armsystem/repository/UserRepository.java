package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByNic(String nic);

    List<User> findByEnabled(boolean flag);

    int countByNic(String nic);
}
