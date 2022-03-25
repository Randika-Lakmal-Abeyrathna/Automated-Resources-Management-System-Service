package com.tsd.armsystem.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.tsd.armsystem.dto.TeacherTransferRequest;
import com.tsd.armsystem.exception.SchoolException;
import com.tsd.armsystem.exception.TeacherException;
import com.tsd.armsystem.exception.TeacherTransferException;
import com.tsd.armsystem.model.Province;
import com.tsd.armsystem.model.Request;
import com.tsd.armsystem.model.School;
import com.tsd.armsystem.model.Teacher;
import com.tsd.armsystem.repository.ProvinceRepository;
import com.tsd.armsystem.repository.SchoolRepository;
import com.tsd.armsystem.repository.TeacherTransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TeacherTransferService {

    private final TeacherTransferRepository teacherTransferRepository;
    private final SchoolService schoolService;
    private final TeacherService teacherService;
    private final ProvinceService provinceService;
    private final ProvinceRepository provinceRepository;

    public Request getRequestByTeacher(Teacher teacher){
        return teacherTransferRepository.findByTeacher(teacher).orElseThrow(()-> new TeacherException("Teacher Not Found"));
    }

//    public List<Request> getRequestByProvince(Province province){
//        return teacherTransferRepository.findByProvince(province);
////        School school = schoolRepository.findByIdschool(id).orElseThrow(() -> new SchoolException("School Not Found"));
////        return carderService.getCarderBySchool(school);
//    }

    public Request addTeacherRequest (TeacherTransferRequest teacherTransferRequest) {

        Request request = new Request();
        Teacher teacher = teacherService.getTeacherById(teacherTransferRequest.getTeacherId());
//        Status --> 0 --> pending
        List<Request> list = teacherTransferRepository.findByTeacherAndStatus(teacher, 0);
        if (list.size() >0){
            throw new TeacherTransferException("Transfer Already In progress");
        }

        School school = schoolService.getSchoolById(teacherTransferRequest.getSchoolId());
        request.setSchool(school);


        request.setTeacher(teacher);

        Province province = provinceService.getProvinceById(teacherTransferRequest.getProvinceId());
        request.setProvince(province);

        String teacherType = teacher.getTeacherType().getType();
        String type= null;

        if (teacherType.equalsIgnoreCase("teacher-provincial")){
            type="provincial";
        }else{
            type="all";
        }
        request.setType(type);
// status --> 0 --> pending
        request.setStatus(0);

        String comment = teacherTransferRequest.getComment();
        request.setComment(comment);

        return teacherTransferRepository.save(request);
    }
}
