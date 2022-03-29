package com.tsd.armsystem.service;

import com.tsd.armsystem.dto.RequestOnboardingRequest;
import com.tsd.armsystem.exception.RequestException;
import com.tsd.armsystem.exception.RequestOnBoardingException;
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



}
