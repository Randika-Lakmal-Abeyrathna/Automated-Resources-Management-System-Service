package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.CityException;
import com.tsd.armsystem.model.City;
import com.tsd.armsystem.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CityService {
     private final CityRepository cityRepository;

     public City getCityById(Integer cityId){
          return cityRepository.findByIdcity(cityId).orElseThrow(()-> new CityException("City Not Found"));
     }


}
