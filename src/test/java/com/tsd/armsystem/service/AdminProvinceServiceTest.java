package com.tsd.armsystem.service;

import com.tsd.armsystem.model.AdminProvince;
import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.model.User;
import com.tsd.armsystem.repository.AdminProvinceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AdminProvinceServiceTest {

    @Mock
    private AdminProvinceRepository adminProvinceRepository;

    private AdminProvinceService adminProvinceServiceTest;

    List<AdminProvince> adminProvince= new ArrayList<>();


    @BeforeEach
    void setUp(){
        adminProvinceServiceTest = new AdminProvinceService(adminProvinceRepository);

        AdminProvince province = AdminProvince.builder().
                id(1)
                .province(Province.builder().id(1).name("Central").build())
                .user(User.builder().nic("909090953V").firstName("randika").build())
                .build();

        adminProvince.add(province);

    }

    @Test
    void testGetAdminProvinceByUser(){
        when(adminProvinceRepository.findByUser("909090953V")).thenReturn(adminProvince);

        List<AdminProvince> results = adminProvinceServiceTest.getAdminProvinceByUser("909090953V");

        assertThat(results).isEqualTo(adminProvince);

    }



}