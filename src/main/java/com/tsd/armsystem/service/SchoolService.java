package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.SchoolRequest;
import com.tsd.armsystem.exception.SchoolException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.s;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SchoolService {
     private final SchoolRepository schoolRepository;
     private final CityService cityService;
     private final  ZonalService zonalService;
     private final SchoolTypeService schoolTypeService;
     private final ProvinceService provinceService;
     private final DistrictService districtService;

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
          City city = cityService.getCityById(schoolRequest.getCityId());
          Zonal zonal = zonalService.getZonalById(schoolRequest.getZonalId());
          SchoolType schoolType = schoolTypeService.getSchoolTypeById(schoolRequest.getSchoolTypeId());
          school.setCity(city);
          school.setZonal(zonal);
          school.setSchoolType(schoolType);

          return schoolRepository.save(school);
     }

     public List<School> getAllSchools(){
          return schoolRepository.findAll();
     }

     public List<School> getSchoolByProvince(Integer provinceId){
          List<School> schoolList = new ArrayList<>();
          List<District> allDistrictByProvince = districtService.getAllDistrictByProvince(provinceId);
          List<Zonal> zonalByDistrict = zonalService.getZonalByDistrict(allDistrictByProvince);

          for (Zonal z: zonalByDistrict) {
               List<School> byZonal = schoolRepository.findByZonal(z);
               for (School s: byZonal) {
                    schoolList.add(s);
               }
          }
          return schoolList;
     }


}
