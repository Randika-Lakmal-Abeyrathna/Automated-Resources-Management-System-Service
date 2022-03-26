package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.RequestException;
import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.model.Request;
import com.tsd.armsystem.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    private final ProvinceService provinceService;

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
}
