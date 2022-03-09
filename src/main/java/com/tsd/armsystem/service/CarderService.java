package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.GenderException;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.Gender;
import com.tsd.armsystem.repository.CarderRepository;
import com.tsd.armsystem.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CarderService {
    private final CarderRepository carderRepository;

    public Carder addCarder(Carder carder){

        return carderRepository.save(carder);
    }

}
