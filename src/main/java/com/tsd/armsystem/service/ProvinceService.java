package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.ProvinceException;
import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.repository.ProvinceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public Province getProvinceById(Integer id){
        return provinceRepository.findById(id).orElseThrow(()-> new ProvinceException("Province Not Found"));
    }

    public List<Province> getAllProvinces(){
        return provinceRepository.findAll();
    }

}
