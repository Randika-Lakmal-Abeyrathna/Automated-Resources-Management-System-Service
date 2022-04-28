package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.RequestOnboardingRequest;
import com.tsd.armsystem.dto.RequestRejectRequest;
import com.tsd.armsystem.dto.TeacherTransferData;
import com.tsd.armsystem.dto.UpdateRequest;
import com.tsd.armsystem.exception.RequestException;
import com.tsd.armsystem.exception.RequestOnBoardingException;
import com.tsd.armsystem.exception.TeacherException;
import com.tsd.armsystem.model.*;
import com.tsd.armsystem.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    private final ProvinceService provinceService;
    private final CarderRepository carderRepository;
    private final RequestOnboardingRepository requestOnboadingRepository;
    private final CarderService carderService;
    private final MailService mailService;
    private final SchoolService schoolService;
    private final FormerExperienceRepository formerExperienceRepository;
    private final TeacherRepository teacherRepository;
    private final UserService userService;


    public List<Request> getAllZonalRequest(Integer provinceId){
        String type = "provincial";
        Province province = provinceService.getProvinceById(provinceId);
//        Status => 0 --> pending status
        List<Request> list = requestRepository.findByTypeAndProvinceAndStatus(type,province,0);
        return list;
    }

    public List<Request> getAllNonZonalRequest(){
        String type ="all";
//        Status => 0 --> pending status
        List<Request> list = requestRepository.findByTypeAndStatus(type, 0);

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


    public void approveRequest(RequestOnboardingRequest requestOnboardingRequest){
        Request request = getRequestById(requestOnboardingRequest.getRequestId());

//        Status --> approve -->1
        request.setStatus(1);
        Request updatedRequest = requestRepository.save(request);

        RequestOnboarding requestOnboarding = new RequestOnboarding();
        requestOnboarding.setRequest(updatedRequest);
        String requestDate = requestOnboardingRequest.getDate();
        try{

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(requestDate);
            requestOnboarding.setAppointmentDate(date);
        } catch (ParseException e) {
            throw new RequestException("Invalid Data format");
        }
//        Status -->pending --> 0
        requestOnboarding.setStatus(0);

        Carder carder = carderService.getCarderById(requestOnboardingRequest.getCarderId());
        carder.setCurrent(carder.getCurrent()+1);

        Carder updatedCarder = carderRepository.save(carder);

        requestOnboarding.setCarder(updatedCarder);

        requestOnboadingRepository.save(requestOnboarding);

        String teacherEmail = request.getTeacher().getUser().getEmail();

        mailService.sendMail(new NotificationEmail(teacherEmail,"Transfer Request Approved.","Requested Transfer is approved. \n " +
                "School Name : "+carder.getSchool().getName()+" and Appointment date is on : "+requestDate+" "));

    }


    public void rejectRequest(RequestRejectRequest requestRejectRequest){
        Request request = getRequestById(requestRejectRequest.getId());
        //Reject status -->2
        request.setStatus(2);
        request.setComment(requestRejectRequest.getComment());

        requestRepository.save(request);
    }

    public List<RequestOnboarding> getAllPendingOnBoardingRequestsBySchoolId(Integer schoolId){
        School school = schoolService.getSchoolById(schoolId);
        List<Carder> carderList = carderService.getCarderBySchool(school);

        List<RequestOnboarding> allPendingOnBoardingRequests = new ArrayList<>();

        for (Carder c: carderList) {
            List<RequestOnboarding> requestOnboardingList = requestOnboadingRepository.findByCarderAndStatus(c, 0);

            for (RequestOnboarding r: requestOnboardingList
                 ) {
                allPendingOnBoardingRequests.add(r);
            }

        }


        return allPendingOnBoardingRequests;
    }

    public List<TeacherTransferData> getAllRequestForSchool(Integer schoolId){
        List<TeacherTransferData> teacherTransferData = new ArrayList<>();

        School school = schoolService.getSchoolById(schoolId);

        List<Teacher> teacherList = teacherRepository.findBySchool(school);

        for (Teacher t : teacherList) {
            List<Request> requestList = requestRepository.findByTeacher(t);

            for (Request r:requestList) {
                if (r.getStatus() == 1){
                    RequestOnboarding byRequest = requestOnboadingRepository.findByRequest(r).orElseThrow(() -> new RequestOnBoardingException("On Board request not found"));
                    if (byRequest.getStatus() ==0){
                        TeacherTransferData transferData = new TeacherTransferData();
                        transferData.setNic(r.getTeacher().getUser().getNic());
                        transferData.setName(r.getTeacher().getUser().getFirstName() + " "+ r.getTeacher().getUser().getLastName());
                        transferData.setContactNo(r.getTeacher().getUser().getContactNumber1());
                        transferData.setRequestType(r.getType().toUpperCase());
                        transferData.setComment(r.getComment());
                        transferData.setRequestStatus("Approved");
                        transferData.setOnboardStatus("Pending");
                        transferData.setAppointmentDate(byRequest.getAppointmentDate().toString());
                        teacherTransferData.add(transferData);
                    }



                }else {
                    TeacherTransferData transferData = new TeacherTransferData();
                    transferData.setNic(r.getTeacher().getUser().getNic());
                    transferData.setName(r.getTeacher().getUser().getFirstName() + " "+ r.getTeacher().getUser().getLastName());
                    transferData.setContactNo(r.getTeacher().getUser().getContactNumber1());
                    transferData.setComment(r.getComment());
                    transferData.setRequestType(r.getType().toUpperCase());
                    if (r.getStatus() == 0){
                        transferData.setRequestStatus("Pending");
                    }else if(r.getStatus() == 2){
                        transferData.setRequestStatus("Reject");
                    }
                    transferData.setOnboardStatus("-");
                    transferData.setAppointmentDate("-");

                    teacherTransferData.add(transferData);
                }
            }

        }


        return teacherTransferData;
    }

    public void approveOnBoardingTeacher(Integer id){
        RequestOnboarding onboarding = requestOnboadingRepository.findById(id).orElseThrow(() -> new RequestOnBoardingException("On Board request not found"));

        Teacher teacher = onboarding.getRequest().getTeacher();

        // save Former experience
        FormerExperiance formerExperiance = new FormerExperiance();
        formerExperiance.setTeacher(teacher);
        formerExperiance.setAppointntdate(teacher.getAppointmentdate());
        formerExperiance.setSchool(teacher.getSchool());
        try{
            formerExperiance.setAppointmentenddate(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        }catch (ParseException e) {
            throw new RequestException("Invalid Data format");
        }


        formerExperienceRepository.save(formerExperiance);

        // Update Teacher Table
        teacher.setSchool(onboarding.getCarder().getSchool());
        teacher.setAppointmentdate(onboarding.getAppointmentDate());

        teacherRepository.save(teacher);

        // Update Onboard request
        // Status --> Approve -->1
        onboarding.setStatus(1);
        requestOnboadingRepository.save(onboarding);

    }

    public List<Request> getRequestByNic(String nic){

        User user = userService.getUserForTeacherByNIC(nic);
        Teacher teacher = teacherRepository.findByUser(user).orElseThrow(()->new TeacherException("Teacher Not found"));

        return requestRepository.findByTeacher(teacher);

    }

    public void updateRequest(UpdateRequest updateRequest){

        Request request = requestRepository.findById(updateRequest.getId()).orElseThrow(() -> new RequestException("Request Not Found"));

        if (updateRequest.getSchoolId() > 0){
            School school = schoolService.getSchoolById(updateRequest.getSchoolId());
            request.setSchool(school);
        }

        if (updateRequest.getProvinceId() > 0){
            Province province = provinceService.getProvinceById(updateRequest.getProvinceId());
            request.setProvince(province);
        }

        request.setComment(updateRequest.getComment());

        requestRepository.save(request);



    }



}
