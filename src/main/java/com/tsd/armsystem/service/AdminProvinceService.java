package com.tsd.armsystem.service;

import com.tsd.armsystem.model.AdminProvince;
import com.tsd.armsystem.repository.AdminProvinceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AdminProvinceService {

    private final AdminProvinceRepository adminProvinceRepository;

    public List<AdminProvince> getAdminProvinceByUser(String nic){
        return adminProvinceRepository.findByUser(nic);
    }
}
