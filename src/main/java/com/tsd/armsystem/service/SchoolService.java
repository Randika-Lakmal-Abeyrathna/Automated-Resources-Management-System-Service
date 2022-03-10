package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.SchoolException;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SchoolService {
     private final SchoolRepository schoolRepository;
     private CarderService carderService;

     public School getSchoolById(Integer schoolId){
          return schoolRepository.findByIdschool(schoolId).orElseThrow(()-> new SchoolException("School Not Found"));
     }

     public List<Carder> getSchoolCarderBySchoolId(Integer id){
          School school = schoolRepository.findByIdschool(id).orElseThrow(() -> new SchoolException("School Not Found"));
          return carderService.getCarderBySchool(school);
     }


}
