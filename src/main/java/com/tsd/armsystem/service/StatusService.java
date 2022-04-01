package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.StatusException;
import com.tsd.armsystem.model.Status;
import com.tsd.armsystem.model.UserType;
import com.tsd.armsystem.repository.StatusRepository;
import com.tsd.armsystem.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class StatusService {

     private final StatusRepository statusRepository;

     public List<Status> getAllStatus(){
          return statusRepository.findAll();
     }

     public Status getStatusById(Integer id){
          return statusRepository.findByIdstatus(id).orElseThrow(()-> new StatusException("Status Not found"));
     }
}
