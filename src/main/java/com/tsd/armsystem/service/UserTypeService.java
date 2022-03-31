package com.tsd.armsystem.service;

import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserTypeService {

     private final UserTypeRepository userTypeRepository;

     public List<UserType> getAllUserType(){
          return userTypeRepository.findAll();
     }


}
