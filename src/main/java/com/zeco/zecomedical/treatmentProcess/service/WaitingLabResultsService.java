package com.zeco.zecomedical.treatmentProcess.service;


import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.WaitingLabResultsRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.Users;
import com.zeco.zecomedical.model.WaitingLabResults;
import com.zeco.zecomedical.treatmentProcess.dto.WaitingLabResultsRequest;
import com.zeco.zecomedical.treatmentProcess.dto.WaitingLabResultsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class WaitingLabResultsService {

    private final WaitingLabResultsRepository waitingLabResultsRepository;
    private FindingUsers findingUsers;

    public WaitingLabResultsResponse savePendingLabRequest(WaitingLabResultsRequest req){

      Users user =  findingUsers.findUserByTheUsername("user not found");
      Doctors doctor = findingUsers.findTheDoctorByUserID(user);

        //when the doctor send any request to a lab ,we store a refrence on this table , so we can later check the following labs if the results are available
        WaitingLabResults waitingLabResults = WaitingLabResults.builder()
                .doctorID(doctor.getDoctor_id())
                .patientID(req.getPatientID())
                .labName(req.getLabName())
                .completed(false)
                .dateTime(LocalDateTime.now())
                .consultationID(req.getConsultationID())
                .build();

        waitingLabResultsRepository.save(waitingLabResults);


        //returning the exact data back , because frontend will use it
        return WaitingLabResultsResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("waiting for results from lab, check later")
                .doctorID(doctor.getDoctor_id())
                .patientID(req.getPatientID())
                .labName(req.getLabName())
                .completed(false)
                .dateTime(LocalDateTime.now())
                .consultationID(req.getConsultationID())
                .build();

    }




    public List<WaitingLabResultsResponse> getPendingLabRequest(){

        Users user =  findingUsers.findUserByTheUsername("user not found");
        Doctors doctor = findingUsers.findTheDoctorByUserID(user);

       Optional<List<WaitingLabResults>> waitingLabResults = waitingLabResultsRepository.findByDoctorIDAndAndCompleted(doctor.getDoctor_id(), false);

       if(waitingLabResults.isEmpty()){
           return Collections.emptyList();
       }

       List<WaitingLabResultsResponse> resultsResponses = waitingLabResults.get().stream().map( el -> {
            return WaitingLabResultsResponse.builder()
                    .id(el.getId())
                   .doctorID(el.getDoctorID())
                   .patientID(el.getPatientID())
                   .labName(el.getLabName())
                   .completed(el.getCompleted())
                   .dateTime(el.getDateTime())
                    .consultationID(el.getConsultationID())
                   .build();

       } ).toList();

       return  resultsResponses;

    }





    public RequestResponse markWaitingResultAsComplete(String consultationID){

        UUID id = UUID.fromString(consultationID);

        Optional<WaitingLabResults> waitingLabResults = waitingLabResultsRepository.findByConsultationID(id);
        if(waitingLabResults.isEmpty()) throw new RuntimeException("results not found");

        WaitingLabResults result = waitingLabResults.get();

        result.setCompleted(true);
        waitingLabResultsRepository.save(result);

        return RequestResponse.builder()
                .status(HttpStatus.OK.value())
                .message("result updated successfully")
                .build();
    }
}
