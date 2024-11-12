package com.zeco.zecomedical.laboratory.service;

import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.general.repositories.*;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.laboratory.dto.TestResults;
import com.zeco.zecomedical.model.LabMicrobiology;
import com.zeco.zecomedical.model.LabTechnicians;
import com.zeco.zecomedical.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LaboratoryService {

    private final FindingUsers findingUsers;
    private final LabTechnicianRepository labTechnicianRepository;


    private final LabBloodBankRepository labBloodBankRepository;
    private  final LabImmunologyRepository labImmunologyRepository;
    private final LabParasitologyRepository labParasitologyRepository;


    public LabTechnicians getLabDepartment(){
      Users user =  findingUsers.findUserByTheUsername("user not found");
      LabTechnicians labTechnician = findingUsers.findTheLabTechnicianByUserID(user);

      return  labTechnician;

    }




}
