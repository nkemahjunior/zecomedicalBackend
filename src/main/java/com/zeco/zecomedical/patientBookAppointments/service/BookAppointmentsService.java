package com.zeco.zecomedical.patientBookAppointments.service;


import com.zeco.zecomedical.general.projections.patient.doctorsAvailable.DoctorsAvailableProjection;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.general.utils.MyDebug;
import com.zeco.zecomedical.patientBookAppointments.dtos.AppointmentRequestRequest;
import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.*;
import com.zeco.zecomedical.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
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



        if(Objects.equals(data.getReason(), "consultation") || Objects.equals(data.getReason(), "checkup")){


            Users user1 = findingUsers.findUserByTheUsername("error getting user ,login again");

            Optional<RegisteredPatients> patient = patientRepository.findByPatientID(user1);
            if(patient.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"error getting user ,login again" );

            LocalDateTime dateTime = LocalDateTime.of(data.getStartYear(),data.getStartMonth(),data.getStartDay(),0,0);


            if(appointmentRequestsRepository.existsByPatientIDAndDateTime(patient.get(),dateTime)){
                return RequestResponse.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message("you already booked an appointment at that time")
                        .build();
            }


            Optional<Doctors> doc = doctorsRepository.findById(data.getDoctor_id());
            Optional<DoctorsAvailableForAppointment> appointment = doctorsAvailableRepository.findById(data.getAppointment_id());


            if(doc.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "error getting doctor with id " + data.getDoctor_id());
            //if(patientsOptional.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"patient with id "+data.getPatient_id()+" not found");
            if(appointment.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "appointment with id "+data.getAppointment_id() +" not found" );




            AppointmentRequests appointmentRequests = AppointmentRequests.builder()
                    .status("PENDING")
                    .reason(data.getReason())
                    .complainNotes(data.getComplain_notes())
                    //.rende_vouz(data.getRende_vouz()) why are you asking if its a rende-vouz when u can get that info from the reason
                    .doctorID(doc.get())
                    .patientID(patient.get())
                    .appointment_id(appointment.get())
                    .dateTime(dateTime)
                    .build();

            appointmentRequestsRepository.save(appointmentRequests);

            return RequestResponse.builder()
                    .status(HttpStatus.CREATED.value())
                    .message("appointment created, you will receive an email once the doctor Accepts/ Rejects your appointment")
                    .build();


        }else {
            throw new RuntimeException("wrong appointment request reason ");
        }

    }


}



























