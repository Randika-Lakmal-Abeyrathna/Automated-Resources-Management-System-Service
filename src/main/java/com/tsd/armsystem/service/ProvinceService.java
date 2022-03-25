package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.SchoolRequest;
import com.tsd.armsystem.exception.ProvinceException;
import com.tsd.armsystem.exception.SchoolException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.ProvinceRepository;
import com.tsd.armsystem.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ProvinceService {

     private final ProvinceRepository provinceRepository;

     public Province getProvinceById(Integer provinceId){
          return provinceRepository.findById(provinceId).orElseThrow(()-> new ProvinceException("Province Not Found"));
     }

}
