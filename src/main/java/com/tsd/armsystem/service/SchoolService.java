package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.SchoolRequest;
import com.tsd.armsystem.exception.SchoolException;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.s;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SchoolService {
     private final SchoolRepository schoolRepository;
     /**
      * private CityService cityService;
      * **/
     private CarderService carderService;

     public School getSchoolById(Integer schoolId){
          return schoolRepository.findByIdschool(schoolId).orElseThrow(()-> new SchoolException("School Not Found"));
     }

     public List<Carder> getSchoolCarderBySchoolId(Integer id){
          School school = schoolRepository.findByIdschool(id).orElseThrow(() -> new SchoolException("School Not Found"));
          return carderService.getCarderBySchool(school);
     }

     public School addSchool(SchoolRequest schoolRequest){
          School school = new School();
          school.setName(schoolRequest.getName());

          // City --->
          /**CityRepository -->Optional<City> findByIdCity(Integer id);
           CityService --> getSchoolById() -> check
           CityException
           ------
           City city = cityService.getCityById(schoolRequest.getcityid());
           school.setCity(city);
           **/
          // Zonal
          //School Type

          return schoolRepository.save(school);
     }


}
