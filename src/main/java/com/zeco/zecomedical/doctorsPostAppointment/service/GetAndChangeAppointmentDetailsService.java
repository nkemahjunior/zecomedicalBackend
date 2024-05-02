package com.zeco.zecomedical.doctorsPostAppointment.service;

import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.doctorsPostAppointment.dtos.MyAppointmentsResponse;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.AppointmentRequestsRepository;
import com.zeco.zecomedical.general.repositories.DoctorsRepository;
import com.zeco.zecomedical.general.repositories.PatientRepository;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.model.AppointmentRequests;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.model.Users;
import com.zeco.zecomedical.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAndChangeAppointmentDetailsService {

    private final FindingUsers findingUsers;
    private final AppointmentRequestsRepository appointmentRequestsRepository;
    private final UsersRepository usersRepository;
    private final DoctorsRepository doctorsRepository;
    private final PatientRepository patientRepository;
    private final EmailService emailService;


//TODO , CREATE THIS SAME METHOD BUT FOR APPOINTMENTS FROM TODAY AND ABOVE(UPCOMING), APPOINTMENTS TODAY BACKWARD(PAST),then filter appointment by patient name
    public List<MyAppointmentsResponse> getUpcomingAppointmentRequestsForADoctor(){

        Users user = findingUsers.findUserByTheUsername("user not found");
        Doctors doctor = findingUsers.findTheDoctorByUserID(user);



       //List<AppointmentRequests> myAppointments = appointmentRequestsRepository.findByDoctorID(doctor);
       List<AppointmentRequests> myAppointments = appointmentRequestsRepository.findByDoctorIDAndDateTimeGreaterThan(doctor,LocalDateTime.now());

       if(myAppointments.isEmpty()) return  Collections.emptyList();

       return  myAppointments.stream().map(e -> MyAppointmentsResponse.builder()
                .id(e.getId())
                //.day(e.getDay())
                //.time_from(e.getTime_from())
                //.time_to(e.getTime_to())
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


    public List<MyAppointmentsResponse> getAcceptedUpcomingAppointments(){

        Users user = findingUsers.findUserByTheUsername("user not found");
        Doctors doctor = findingUsers.findTheDoctorByUserID(user);


        List<AppointmentRequests> myAppointments = appointmentRequestsRepository.findByDoctorIDAndStatusAndDateTimeGreaterThan(doctor,"ACCEPTED",LocalDateTime.now());

        if(myAppointments.isEmpty()) return  Collections.emptyList();

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



    public List<MyAppointmentsResponse> getAcceptedUpcomingAppointmentsFilterByPatientName(String patientName){

        Users user = findingUsers.findUserByTheUsername("user not found");
        Doctors doctor = findingUsers.findTheDoctorByUserID(user);

        Optional<Users> userPatient = usersRepository.findByName(patientName);
        if(userPatient.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "patient not found");

        Optional<RegisteredPatients> patientOptional = patientRepository.findByPatientID(userPatient.get());

        List<AppointmentRequests> myAppointments = appointmentRequestsRepository.findByDoctorIDAndPatientIDAndStatusAndDateTimeGreaterThan(doctor,patientOptional.get(),"ACCEPTED",LocalDateTime.now());

        if(myAppointments.isEmpty()) return  Collections.emptyList();

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







    public RequestResponse updateAppointmentStatus(Long id, String status)  {

        if(status.equals("ACCEPTED") || status.equals("DECLINED") || status.equals("RESCHEDULED")) {

            Optional<AppointmentRequests> appointment = appointmentRequestsRepository.findById(id);
            if (appointment.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "appointment not found");

            Optional<Doctors> doctor = doctorsRepository.findById(appointment.get().getDoctorID().getDoctor_id());
            if(doctor.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "appointment doctor  not found");

            Optional<RegisteredPatients> patient = patientRepository.findById(appointment.get().getPatientID().getId());
            if(patient.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "appointment patient  not found");

            String patientName = patient.get().getPatientID().getName();
            String doctorName = doctor.get().getUuid().getName();
            String reason = appointment.get().getReason();
            LocalDate appointmentDate = LocalDate.from(appointment.get().getAppointment_id().getTimeFrom());


            AppointmentRequests appointment1 = appointment.get();
            appointment1.setStatus(status);
            appointmentRequestsRepository.save(appointment1);

            //async method
            emailService.appointmentStatusEmail(patientName,doctorName,reason,appointmentDate,status);



            //emailService.sendMail();

            return RequestResponse.builder()
                    .status(HttpStatus.ACCEPTED.value())
                    .message("status updated successfully")
                    .build();
        }else{
            throw new MyException(HttpStatus.NOT_ACCEPTABLE.value(), "value should either be : ACCEPTED or DECLINED or RESCHEDULED");
        }
    }




}
