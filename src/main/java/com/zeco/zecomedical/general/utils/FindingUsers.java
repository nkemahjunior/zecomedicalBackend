package com.zeco.zecomedical.general.utils;

import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.general.repositories.DoctorsRepository;
import com.zeco.zecomedical.general.repositories.PatientRepository;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindingUsers {

    private final UsersRepository usersRepository;
    private  final DoctorsRepository doctorsRepository;
    private  final PatientRepository patientRepository;

    public Users findUserByTheUsername(String error){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if(username == null) throw new MyException(HttpStatus.NOT_FOUND.value(),"user not found,login");

        Optional<Users> userOptional = usersRepository.findByUsername(username);
        if(userOptional.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),error);

        return userOptional.get();
    }

    public Doctors findTheDoctorByUserID(Users user){

        Optional<Doctors> doctor = doctorsRepository.findByUuid(user);
        if(doctor.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value()," doctor not found");

        return doctor.get();
    }

    public RegisteredPatients findThePatientByUserID(Users user){

        Optional<RegisteredPatients>  patient = patientRepository.findByPatientID(user);
        if (patient.isEmpty())  throw new MyException(HttpStatus.NOT_FOUND.value()," patient not found");

        return patient.get();
    }
}
