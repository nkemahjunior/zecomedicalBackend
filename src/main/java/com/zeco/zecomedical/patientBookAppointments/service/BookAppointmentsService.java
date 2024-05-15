package com.zeco.zecomedical.patientBookAppointments.service;


import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.patientBookAppointments.dtos.AppointmentRequestRequest;
import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.*;
import com.zeco.zecomedical.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookAppointmentsService {

    private final AppointmentRequestsRepository appointmentRequestsRepository;
    private final DoctorsRepository doctorsRepository;
    private final PatientRepository patientRepository;
    private final DoctorsAvailableRepository doctorsAvailableRepository;
    private final UsersRepository usersRepository;

    private final FindingUsers findingUsers;

   /* public AvailableAppointmentsResponse getAllDoctorsAvailableForAppointment(){

       List<DoctorsAvailableForAppointment> available = doctorsAvailableRepository.findAll();

    //Stream.toList() allows null values in A list if found,collector.toList() does same,collectors.toUnmodifiableList() throws a null pointer exception
        //Amongst the three,only collector.toList() gives you a mutable list, the other 2 give an immutable list
      List< Optional<Doctors> > doctors = available.stream().map(e -> doctorsRepository.findById( e.getId())).toList();


    }*/

    public List<DoctorsAvailableForAppointment> getAllDoctorsAvailableForAppointmentFilterBySpeciality(String speciality){

       List<Doctors> doctors = doctorsRepository.findBySpeciality(speciality);

      List<DoctorsAvailableForAppointment> doctorBySpeciality = new ArrayList<>();
      LocalDateTime today = LocalDateTime.now();

      doctors.forEach( el -> {
          //TODO bad work boy, you are suppose to filter while fetching from the database not fetching all before filtering, make and arrange this
           List<DoctorsAvailableForAppointment> doc = doctorsAvailableRepository.findByDoctorID(el);
           doc.forEach( e -> {
               if(e != null && e.getTimeFrom().isAfter(today))
                   doctorBySpeciality.add(e);
           });

      });

      return doctorBySpeciality;

    }

    public RequestResponse bookAppointment(AppointmentRequestRequest data){



        //if(authentication.getName() == null ) throw new MyException(HttpStatus.UNAUTHORIZED.value(),"error getting user,login"); //useless since spring security will not let you get to this method if you are not authenticated
        //LocalDateTime startTime = LocalDateTime.of(data.getStartYear(),data.getStartMonth(),data.getStartDayNumber(),data.getTime_from_hour(), data.getTime_from_min());
        //LocalDateTime endTime = LocalDateTime.of(data.getEndYear(),data.getEndMonth(),data.getEndDayNumber(),data.getTime_to_hour(), data.getTime_to_min());

       // if(startTime.isAfter(endTime)) throw new MyException(HttpStatus.BAD_REQUEST.value(),"start time can not be low than end time");


        Users user1 = findingUsers.findUserByTheUsername("error getting user ,login again");

        Optional<RegisteredPatients> patient = patientRepository.findByPatientID(user1);
        if(patient.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"error getting user ,login again" );


        Optional<Doctors> doc = doctorsRepository.findById(data.getDoctor_id());
        Optional<DoctorsAvailableForAppointment> appointment = doctorsAvailableRepository.findById(data.getAppointment_id());


        if(doc.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "error getting doctor with id " + data.getDoctor_id());
        //if(patientsOptional.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"patient with id "+data.getPatient_id()+" not found");
        if(appointment.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "appointment with id "+data.getAppointment_id() +" not found" );



        AppointmentRequests appointmentRequests = AppointmentRequests.builder()
                .status("PENDING")
                .reason(data.getReason())
                .complain_notes(data.getComplain_notes())
                //.rende_vouz(data.getRende_vouz()) why are you asking if its a rende-vouz when u can get that info from the reason
                .doctorID(doc.get())
                .patientID(patient.get())
                .appointment_id(appointment.get())
                .dateTime(LocalDateTime.of(data.getStartYear(),data.getStartMonth(),data.getStartDay(),0,0))
                .build();

           appointmentRequestsRepository.save(appointmentRequests);

            return RequestResponse.builder()
                    .status(HttpStatus.CREATED.value())
                    .message("appointment created")
                    .build();



    }


}



























