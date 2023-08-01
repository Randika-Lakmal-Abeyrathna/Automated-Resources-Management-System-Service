package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.CarderRequest;
import com.tsd.armsystem.exception.CarderException;
import com.tsd.armsystem.model.Carder;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Subjects;
import com.tsd.armsystem.repository.CarderRepository;
import com.tsd.armsystem.repository.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CarderServiceTest {

    @Mock
    private CarderRepository carderRepository;

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private SubjectService subjectService;

    private CarderService carderServiceTest;

    Carder carder;

    CarderRequest carderRequest;

    List<Carder> carderList = new ArrayList<>();

    School school;

    @BeforeEach
    void setUp() {
        carderServiceTest = new CarderService(carderRepository,schoolRepository,subjectService);

        carder = Carder.builder().idcarder(1)
                .school(School.builder().idschool(1).name("SACK").build())
                .current(1)
                .limitation(10)
                .subjects(Subjects.builder().id(1).description("test").name("test").build())
                .build();

        carderList.add(carder);

        school = School.builder().idschool(1).name("SACK").build();
        carderRequest =CarderRequest.builder().
                schoolId(1)
                .subjectId(1)
                .current(1)
                .limitation(10)
                .build();

    }

    @Test
    void testGetCarderById(){
        when(carderRepository.findByIdcarder(1)).thenReturn(Optional.of(carder));

        Carder results = carderServiceTest.getCarderById(1);

        assertThat(results).isEqualTo(carder);
    }

    @Test
    void testGetCarderByIdNotFound(){
        when(carderRepository.findByIdcarder(1)).thenReturn(Optional.empty());

        assertThrows(CarderException.class,()-> carderServiceTest.getCarderById(1));
    }

    @Test
    void testGetCarderBySchool(){
        when(carderRepository.findCarderBySchool(school)).thenReturn(carderList);

        List<Carder> results = carderServiceTest.getCarderBySchool(school);

        assertThat(results).isEqualTo(carderList);

    }

    @Test
    void testAddCarder(){
        when(schoolRepository.findByIdschool(1)).thenReturn(Optional.of(school));
        when(carderRepository.save(any(Carder.class))).thenReturn(carder);

        Carder results = carderServiceTest.addCarder(carderRequest);

        assertThat(results.getIdcarder()).isEqualTo(carder.getIdcarder());
    }
}