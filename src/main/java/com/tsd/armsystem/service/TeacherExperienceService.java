package com.tsd.armsystem.service;

import com.tsd.armsystem.model.FormerExperiance;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.repository.FormerExperienceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TeacherExperienceService {

    private final FormerExperienceRepository formerExperienceRepository;

    public List<FormerExperiance> getExperienceByTeacher(Teacher teacher){
        return formerExperienceRepository.findByTeacher(teacher);
    }

}
