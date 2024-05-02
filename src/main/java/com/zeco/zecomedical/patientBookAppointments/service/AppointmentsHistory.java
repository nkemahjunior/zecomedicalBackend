package com.zeco.zecomedical.patientBookAppointments.service;


import com.zeco.zecomedical.doctorsPostAppointment.dtos.MyAppointmentsResponse;
import com.zeco.zecomedical.general.repositories.AppointmentRequestsRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.model.AppointmentRequests;
import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentsHistory {

    private final AppointmentRequestsRepository appointmentRequestsRepository;
    private final FindingUsers findingUsers;

    public List<MyAppointmentsResponse> allPatientAppointmentHistory(){

        Users user = findingUsers.findUserByTheUsername("user not found");
        RegisteredPatients patient = findingUsers.findThePatientByUserID(user);

       List<AppointmentRequests> myAppointments = appointmentRequestsRepository.findByPatientID(patient);

        return  myAppointments.stream().map(e -> MyAppointmentsResponse.builder()
                .id(e.getId())
                .status(e.getStatus())
                .reason(e.getReason())
                .complain_notes(e.getComplain_notes())
                .rende_vouz(e.getRende_vouz())
                .doctorID(e.getDoctorID())
                .patient_id(e.getPatientID())
                .appointment_id(e.getAppointment_id())
                .dateTime(e.getDateTime())
                .build()
        ).toList();

    }



    public List<MyAppointmentsResponse> allPatientUpcomingAppointments(String status){

        Users user = findingUsers.findUserByTheUsername("user not found");
        RegisteredPatients patient = findingUsers.findThePatientByUserID(user);

        List<AppointmentRequests> myAppointments = appointmentRequestsRepository.findByPatientIDAndStatusAndDateTimeGreaterThan(patient,status, LocalDateTime.now());

        return  myAppointments.stream().map(e -> MyAppointmentsResponse.builder()
                .id(e.getId())
                .status(e.getStatus())
                .reason(e.getReason())
                .complain_notes(e.getComplain_notes())
                .rende_vouz(e.getRende_vouz())
                .doctorID(e.getDoctorID())
                .patient_id(e.getPatientID())
                .appointment_id(e.getAppointment_id())
                .dateTime(e.getDateTime())
                .build()
        ).toList();

    }


}
