package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.CityException;
import com.tsd.armsystem.model.City;
import com.tsd.armsystem.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    private CityService cityServiceTest;

    List<City> cityList = new ArrayList<>();
    City city1;
    @BeforeEach
    void setUp() {
        cityServiceTest = new CityService(cityRepository);

         city1= City.builder().name("Kandy").idcity(1).build();
        City city2 = City.builder().name("Colombo").idcity(2).build();

        cityList.add(city1);
        cityList.add(city2);

    }

    @Test
    void testGetCityById() {
        when(cityRepository.findByIdcity(1)).thenReturn(Optional.of(city1));

        City results = cityServiceTest.getCityById(1);

        assertThat(results).isEqualTo(city1);

    }

    @Test
    void testGetCityByIdNotFound(){
        when(cityRepository.findByIdcity(1)).thenReturn(Optional.empty());

        assertThrows(CityException.class,()-> cityServiceTest.getCityById(1));
    }

    @Test
    void testGetAllCity() {
        when(cityRepository.findAll()).thenReturn(cityList);

        List<City> results = cityServiceTest.getAllCity();

        assertThat(results).isEqualTo(cityList);
    }

    @Test
    void testGetCityByName() {
        when(cityRepository.findByName("Kandy")).thenReturn(List.of(city1));

        City results = cityServiceTest.getCityByCity("Kandy");

        assertThat(results.getName()).isEqualTo("Kandy");
    }

    @Test
    void testGetCityByNameNotFound() {
        when(cityRepository.findByName("Kandy")).thenReturn(List.of());

        assertThrows(CityException.class,()-> cityServiceTest.getCityByCity("Kandy"));
    }
}