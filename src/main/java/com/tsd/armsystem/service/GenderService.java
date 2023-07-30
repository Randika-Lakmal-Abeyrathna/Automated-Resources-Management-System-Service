package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.GenderException;
import com.tsd.armsystem.model.Gender;
import com.tsd.armsystem.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@CacheConfig(cacheNames = {"Gender"})
public class GenderService {

    private final GenderRepository genderRepository;

    public Gender addGender(Gender gender){
        genderValidation(gender);
        Gender lowerCaseGender = setGenderNameToLowerCase(gender);
        return genderRepository.save(lowerCaseGender);
    }

    public Integer getCountByGender(String gender){
        return genderRepository.countByGender(gender);
    }

    private void genderValidation(Gender gender){
        String genderName = gender.getGender().toLowerCase();

        if(getCountByGender(genderName)>0){
            throw new GenderException("Gender "+genderName+" already in use");
        }else if(genderName.isBlank() || genderName.isEmpty()){
            throw new GenderException("Gender name can not be empty or blank");
        }

    }

    private Gender setGenderNameToLowerCase(Gender gender){
        gender.setGender(gender.getGender().toLowerCase());
        return gender;
    }

    @CachePut(key = "#result.idgender")
    public Gender updateGender(Gender gender){
        genderValidation(gender);
        Gender newGender = setGenderNameToLowerCase(gender);
        return genderRepository.save(newGender);
    }

    // Find Gender --Start
    public List<Gender> getAllGender(){
        return genderRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Gender findGenderById(Integer id){
        return genderRepository.getGenderByIdgender(id)
                .orElseThrow(()-> new GenderException("Gender by Id "+id+" was not found"));
    }

    public Gender findGenderByGender(String gender){
        return genderRepository.findGenderByGender(gender)
                .orElseThrow(()-> new GenderException("Gender by name "+gender+" was not found"));
    }
    // Find Gender --End

    @CacheEvict(key = "#id")
    public void deleteGenderById(Integer id){
        genderRepository.deleteGenderByIdgender(id);
    }

}
