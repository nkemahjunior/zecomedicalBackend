package com.zeco.zecomedical.doctorsPostAppointment.service;


import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.doctorsPostAppointment.dtos.DoctorsAvailableForAppointmentRequest;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.DoctorsAvailableRepository;
import com.zeco.zecomedical.general.repositories.DoctorsRepository;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.DoctorsAvailableForAppointment;
import com.zeco.zecomedical.model.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostAppointmentService {

    private final DoctorsAvailableRepository doctorsAvailableRepository;
    private final UsersRepository usersRepository;
    private final DoctorsRepository doctorsRepository;


    @Transactional
    public RequestResponse PostAppointmentAsDoctor(HttpServletRequest request, DoctorsAvailableForAppointmentRequest data){

       /* HttpSession session = request.getSession(false);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal()) throw new MyException(HttpStatus.UNAUTHORIZED.value(),"session expired,login");

        //if(session == null ) throw new MyException(HttpStatus.UNAUTHORIZED.value(),"session expired,login");*/

        LocalDateTime time_from = LocalDateTime.of(data.getStartYear(),data.getStartMonth(),data.getStartDayNumber(), data.getTime_from_hour(), data.getTime_from_min());
        LocalDateTime time_to = LocalDateTime.of(data.getEndYear(),data.getEndMonth(),data.getEndDayNumber(),data.getTime_to_hour(), data.getTime_to_min());

        if(time_from.isBefore(LocalDateTime.now()) || time_to.isBefore(LocalDateTime.now()))
            throw new MyException(HttpStatus.BAD_REQUEST.value(), "time for appointment should be from today and above");

        if (time_from.isAfter(time_to))
            throw new MyException(HttpStatus.BAD_REQUEST.value(), "start time should be lesser than end time");

        String username = request.getUserPrincipal().getName();
        Optional<Users> user = usersRepository.findByUsername(username);

        if(user.isEmpty()) throw new RuntimeException();

        Users user1 = user.get();
        //UUID uuid = user1.getId();

        Optional<Doctors> doc = doctorsRepository.findByUuid(user1);
        if(doc.isEmpty()) throw new RuntimeException();

        Doctors doctor = doc.get();
        Doctors doctor1 =  Doctors.builder()
                .doctor_id(doctor.getDoctor_id())
                .build();



        DoctorsAvailableForAppointment available = DoctorsAvailableForAppointment.builder()
                .doctorID(doctor1)
                .day(data.getDay())//TODO delete later,and also delete in the database
                .timeFrom(time_from)
                .time_to(time_to)
                .build();

        doctorsAvailableRepository.save(available);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("appointment created")
                .build();


    }
}
