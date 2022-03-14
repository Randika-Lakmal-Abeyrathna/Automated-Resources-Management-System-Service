package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.SchoolException;
import com.tsd.armsystem.exception.ZonalException;
import com.tsd.armsystem.model.SchoolType;
import com.tsd.armsystem.repository.SchoolTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class SchoolTypeService {
     private final SchoolTypeRepository schoolTypeRepository;

     public SchoolType getSchoolTypeById(Integer schoolTypeId){
          return schoolTypeRepository.findById(schoolTypeId).orElseThrow(()-> new SchoolException("School Type Not Found"));
     }


}
