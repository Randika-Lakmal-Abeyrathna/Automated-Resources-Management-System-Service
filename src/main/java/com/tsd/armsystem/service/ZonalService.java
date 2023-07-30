package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.ZonalException;
import com.tsd.armsystem.model.District;
import com.tsd.armsystem.model.Zonal;
import com.tsd.armsystem.repository.ZonalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ZonalService {
     private final ZonalRepository zonalRepository;

     public Zonal getZonalById(Integer zonalId){
          return zonalRepository.findByIdzonal(zonalId).orElseThrow(()-> new ZonalException("Zone Not Found"));
     }

     public List<Zonal> getAllZonal(){
          return zonalRepository.findAll();
     }

     public List<Zonal> getZonalByDistrict(List<District> districtList){
          List<Zonal> zonalList = new ArrayList<>();
          for (District d: districtList) {
               List<Zonal> byDistrict = zonalRepository.findByDistrict(d);
               for (Zonal z: byDistrict) {
                    zonalList.add(z);
               }
          }

          return zonalList;
     }


}
