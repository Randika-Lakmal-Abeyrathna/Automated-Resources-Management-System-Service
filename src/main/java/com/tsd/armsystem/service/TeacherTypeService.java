package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.TeacherTypeException;
import com.tsd.armsystem.model.TeacherType;
import com.tsd.armsystem.repository.TeacherTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class TeacherTypeService {

    private final TeacherTypeRepository teacherTypeRepository;

    public TeacherType getTeacherTypeById(Integer id){
        return teacherTypeRepository.findById(id).orElseThrow(()->new TeacherTypeException("Teacher Type Not found"));
    }
}
