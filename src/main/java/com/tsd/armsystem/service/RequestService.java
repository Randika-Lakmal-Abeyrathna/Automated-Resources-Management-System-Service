package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.RequestException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    private final ProvinceService provinceService;
    private final CarderRepository carderRepository;


    public List<Request> getAllZonalRequest(Integer provinceId){
        String type = "provincial";
        Province province = provinceService.getProvinceById(provinceId);
//        Status => 0 --> pending status
        List<Request> list = requestRepository.findByTypeAndProvinceAndStatus(type,province,0);
        return list;
    }

    public Request getRequestById(Integer id){
        return requestRepository.findById(id).orElseThrow(()-> new RequestException("Transfer Request NotFound"));
    }

    public List<Carder> getSuggestedSchoolsByRequestId(Integer id){
        Request request = getRequestById(id);
        List<Carder> selectedCarderDetails = new ArrayList<>();
        Set<Subjects> subjectsSet = request.getTeacher().getSubjects();
        Province requestProvince = request.getProvince();

        for (Subjects s:subjectsSet) {
            List<Carder> carderList = carderRepository.findBySubjects(s);
            for (Carder c:carderList) {
                int current = c.getCurrent();
                int limit =c.getLimitation();
                Province province = c.getSchool().getZonal().getDistrict().getProvince();
                if (current < limit && province.equals(requestProvince)){
                    selectedCarderDetails.add(c);
                }
            }
        }

        return selectedCarderDetails;
    }
}
