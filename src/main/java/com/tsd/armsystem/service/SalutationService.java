package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.SalutationException;
import com.tsd.armsystem.model.Salutation;
import com.tsd.armsystem.repository.SalutationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SalutationService {

    private final SalutationRepository salutationRepository;

    public List<Salutation> getAllSalutation(){
        return salutationRepository.findAll();
    }


    public Salutation getSalutationBySalutation(String salutation){
        return salutationRepository.findBySalutation(salutation).stream().findFirst().orElseThrow(()-> new SalutationException("Salutation Not Found"));
    }

    public Salutation getSalutationById(Integer id){
        return salutationRepository.findByIdsalutation(id).orElseThrow(()-> new SalutationException("Salutation Not Found"));
    }

}
