package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.QualificationException;
import com.tsd.armsystem.model.Qualification;
import com.tsd.armsystem.repository.QualificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;

    public Qualification addQualification(Qualification qualification){
        qualificationValidation(qualification);
        return qualificationRepository.save(qualification);
    }

    public List<Qualification> getAllQualification(){
        return qualificationRepository.findAll();
    }

    public Qualification findById(Integer id){
        return qualificationRepository.findById(id).orElseThrow(()-> new QualificationException("Qualification id " +id+ " not found"));
    }

    public Qualification updateQualification(Qualification qualification){
        qualificationValidation(qualification);
        Qualification updateQualification = findById(qualification.getId());
        updateQualification.setName(qualification.getName());
        updateQualification.setDescription(qualification.getDescription());

        return qualificationRepository.save(updateQualification);
    }

    private void qualificationValidation(Qualification qualification){
        String name = qualification.getName();
        String description = qualification.getDescription();

        if (name.isBlank() || name.isEmpty()){
         throw new QualificationException("Qulification Name cannot be empty");
        }
        if (qualificationRepository.countByName(name) >0 && qualificationRepository.countByDescription(description) >0)  {
            throw new QualificationException("Qualification already exists");
        }

    }

}
