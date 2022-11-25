package com.tsd.armsystem.service;

import com.tsd.armsystem.model.AdminProvince;
import com.tsd.armsystem.model.User;
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
    private final UserService userService;

    public List<AdminProvince> getAdminProvinceByUser(String nic){
        return adminProvinceRepository.findByUser(nic);
    }
}
