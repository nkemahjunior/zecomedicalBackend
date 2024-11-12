package com.zeco.zecomedical.patientAndDoctors.service;


import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.general.repositories.AppointmentRequestsRepository;
import com.zeco.zecomedical.general.repositories.DoctorsRepository;
import com.zeco.zecomedical.general.repositories.PatientRepository;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.model.AppointmentRequests;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorPatientsSharedService {

    private final UsersRepository usersRepository;
    private  final DoctorsRepository doctorsRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRequestsRepository appointmentRequestsRepository;

    private final FindingUsers findingUsers;


    public List<AppointmentRequests> getPastAndPresentAppointments(){

        Users user = findingUsers.findUserByTheUsername("user not found");

        //Optional<Doctors> doctor = Optional.empty();
        //Optional<RegisteredPatients> patient = Optional.empty();

        //YOU ALREADY CREATED A METHOD WITH THIS SAME FUNCTIONALITY, CHECK THE GetAllAppointmentRequestsForADoctor()  IN THE GetAndChangeAppointmentStatusService
        if(user.getRole().getId() == 2) {
            Optional<Doctors> doctor = doctorsRepository.findByUuid(user);
            if(doctor.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"user not found");

          return appointmentRequestsRepository.findByDoctorID(doctor.get());
        }
        if(user.getRole().getId() == 1) {
            Optional<RegisteredPatients> patient = patientRepository.findByPatientID(user);
            if(patient.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"user not found");
            return  appointmentRequestsRepository.findByPatientID(patient.get());
        }


        return Collections.emptyList();
    }
}
