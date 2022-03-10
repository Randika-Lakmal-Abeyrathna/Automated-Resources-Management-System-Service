package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.CarderRequest;
import com.tsd.armsystem.exception.SchoolException;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Subjects;
import com.tsd.armsystem.repository.CarderRepository;
import com.tsd.armsystem.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CarderService {

    private final CarderRepository carderRepository;
    private final SchoolRepository schoolRepository;
    private final SubjectService subjectService;

    public List<Carder> getCarderBySchool(School school){
        return carderRepository.findCarderBySchool(school);
    }

    public Carder addCarder(CarderRequest carderRequest){
        Carder carder = new Carder();
        carder.setLimitation(carderRequest.getLimitation());
        carder.setCurrent(carderRequest.getCurrent());
        Subjects subject = subjectService.getSubjectById(carderRequest.getSubjectId());
        School school = schoolRepository.findByIdschool(carderRequest.getSchoolId()).orElseThrow(() -> new SchoolException("School Not Found"));

        carder.setSubjects(subject);
        carder.setSchool(school);

        return carderRepository.save(carder);

    }

}
