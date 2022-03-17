package com.tsd.armsystem.repository;

import com.tsd.armsystem.model.UpdateUser;
import com.tsd.armsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UpdateUserRepository extends JpaRepository<UpdateUser,Integer> {

    List<UpdateUser> findByUser(User user);

    Optional<UpdateUser> findById(Integer id);

}
