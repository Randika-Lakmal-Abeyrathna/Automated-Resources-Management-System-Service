package com.tsd.armsystem.service;


import com.tsd.armsystem.model.MaritalStatus;
import com.tsd.armsystem.repository.MaritalStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MaritalStatusService {

    private final MaritalStatusRepository maritalStatusRepository;

    public List<MaritalStatus> getAllMaritalStatus(){
        return maritalStatusRepository.findAll();
    }
}
