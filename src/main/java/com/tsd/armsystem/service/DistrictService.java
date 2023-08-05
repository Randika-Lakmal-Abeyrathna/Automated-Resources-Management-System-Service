package com.tsd.armsystem.service;

import com.tsd.armsystem.model.District;
import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.repository.DistrictRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DistrictService {

    private final ProvinceService provinceService;
    private final DistrictRepository districtRepository;

    public List<District> getAllDistrictByProvince(Integer provinceId){
        Province provinceById = provinceService.getProvinceById(provinceId);
        return districtRepository.findByProvince(provinceById);
    }

}
