package com.tsd.armsystem.service;

import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.repository.CarderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CarderService {

    private final CarderRepository carderRepository;

    public List<Carder> getCarderBySchool(School school){
        return carderRepository.findCarderBySchool(school);
    }

}
