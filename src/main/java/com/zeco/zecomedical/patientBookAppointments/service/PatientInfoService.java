package com.zeco.zecomedical.patientBookAppointments.service;


import com.zeco.zecomedical.general.repositories.PatientRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.model.Users;
import com.zeco.zecomedical.patientBookAppointments.dtos.PatientInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientInfoService {

    private final PatientRepository patientRepository;
    private final FindingUsers findingUsers;


    public PatientInfoResponse getPatientInfo(){

        Users user = findingUsers.findUserByTheUsername("user not found");
        RegisteredPatients patient = findingUsers.findThePatientByUserID(user);


        return PatientInfoResponse.builder()
                .id(patient.getId())
                .weight(patient.getWeight())
                .bloodGroup(patient.getBloodGroup())
                .bloodPressure(patient.getBloodPressure())
                .email(patient.getPatientID().getEmail())
                .name(patient.getPatientID().getName() )
                .username(patient.getPatientID().getUsername())
                .gender(patient.getPatientID().getGender())
                .dob(patient.getPatientID().getDob())
                .address(patient.getPatientID().getAddress())
                .profilePhotoUrl(patient.getPatientID().getProfilePhotoUrl())
                .build();

    }
}
