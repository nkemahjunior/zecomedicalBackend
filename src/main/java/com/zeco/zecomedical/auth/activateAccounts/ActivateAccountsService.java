package com.zeco.zecomedical.auth.activateAccounts;

import com.zeco.zecomedical.auth.activateAccounts.activationDtos.ActivateDoctorAccountRequest;
import com.zeco.zecomedical.auth.activateAccounts.activationDtos.ActivateLabTechAccountRequest;
import com.zeco.zecomedical.auth.activateAccounts.activationDtos.ActivatePatientAccountRequest;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.DoctorsRepository;
import com.zeco.zecomedical.general.repositories.LabTechnicianRepository;
import com.zeco.zecomedical.general.repositories.PatientRepository;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.model.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActivateAccountsService {

    private final FindingUsers findingUsers;
    private final PatientRepository patientRepository;
    private final DoctorsRepository doctorsRepository;
    private  final LabTechnicianRepository labTechnicianRepository;

    private final UsersRepository usersRepository;


    public RequestResponse activatePatientAccount(ActivatePatientAccountRequest request){

       Users user = findingUsers.findUserByTheUsername("user not found");

        RegisteredPatients patient = RegisteredPatients.builder()
                .weight(request.getWeight())
                .bloodGroup(request.getBloodGroup())
                .bloodPressure(request.getBloodPressure())
                .patientID(user)
                .build();

        patientRepository.save(patient);

        //change user role to patient
        Roles patientRole = new Roles();
        patientRole.setId(3);// role id for patients
        user.setRole(patientRole);

        usersRepository.save(user);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("patient account activated")
                .build();
    }



    public RequestResponse activateDoctorAccount(ActivateDoctorAccountRequest request){

        Users user = findingUsers.findUserByTheUsername("user not found");

        Doctors doctor = Doctors.builder()
                .uuid(user)
                .speciality(request.getSpeciality())
                .build();

        doctorsRepository.save(doctor);

        //change user role to Doctor
        Roles doctorRole = new Roles();
        doctorRole.setId(2);// role id for doctors
        user.setRole(doctorRole);

        usersRepository.save(user);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Doctor account activated")
                .build();
    }


    public RequestResponse activateLabTechnicianAccount(ActivateLabTechAccountRequest request){

        Users user = findingUsers.findUserByTheUsername("user not found");

        Laboratories labDepartment = new Laboratories();
        labDepartment.setId(request.getLabDepartment());

        LabTechnicians labTechnician = LabTechnicians.builder()
                .userID(user)
                .labDepartment(labDepartment)
                .build();

        labTechnicianRepository.save(labTechnician);

        //change user role to lab technician
        Roles labTechnicianRole = new Roles();
        labTechnicianRole.setId(6);// role id for labTechnician
        user.setRole(labTechnicianRole);

        usersRepository.save(user);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Lab Technician account activated")
                .build();
    }
}
