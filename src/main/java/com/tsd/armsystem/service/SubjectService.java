package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.SubjectsException;
import com.tsd.armsystem.model.Subjects;
import com.tsd.armsystem.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;


    public Subjects addSubject(Subjects subjects){
        return subjectRepository.save(subjects);
    }

    public List<Subjects> getAllSubjects(){
        List<Subjects> subjectsList = subjectRepository.findAll();
        return subjectsList;
    }

    public Subjects getSubjectById(Integer id){
        return subjectRepository.findById(id).orElseThrow(()->new SubjectsException("Subject id " + id + " not found"));
    }


}
