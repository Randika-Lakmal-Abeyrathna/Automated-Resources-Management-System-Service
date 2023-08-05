package com.tsd.armsystem.service;

import com.tsd.armsystem.model.District;
import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.repository.DistrictRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DistrictServiceTest {

    @Mock
    private DistrictRepository districtRepository;

    @Mock
    private ProvinceService provinceService;

    private DistrictService districtServiceTest;

    Province province;

    District district;

    List<District> districtList= new ArrayList<>();

    @BeforeEach
    void setUp() {

        districtServiceTest = new DistrictService(provinceService,districtRepository);

        province = Province.builder().name("Central").id(1).build();

        district = District.builder().id(1).name("Kandy").province(province).build();

        District district2 = District.builder().id(2).province(province).name("Matale").build();

        districtList.add(district);
        districtList.add(district2);
    }

    @Test
    void testGetAllDistrictByProvince() {

        when(provinceService.getProvinceById(1)).thenReturn(province);
        when(districtRepository.findByProvince(province)).thenReturn(districtList);

        List<District> results = districtServiceTest.getAllDistrictByProvince(1);

        assertThat(results).isEqualTo(districtList);

    }
}