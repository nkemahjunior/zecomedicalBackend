package com.zeco.zecomedical.laboratory.service;

import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.general.repositories.LabBloodBankRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.general.utils.MyDebug;
import com.zeco.zecomedical.laboratory.dto.TestResults;
import com.zeco.zecomedical.model.LabBloodBank;
import com.zeco.zecomedical.model.LabMicrobiology;
import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LabBloodBankService {

    private final LabBloodBankRepository labBloodBankRepository;
    private final FindingUsers findingUsers;


    public Page<LabRequestProjections> getLabRequesBloodBank(Integer page, Integer size){
        return  labBloodBankRepository.findByCompleted(false, PageRequest.of(page,size));
    }


    //only for patients
    public List<LabRequestProjections> getLabRequestBloodBankPatient(){

        Users user = findingUsers.findUserByTheUsername("not found");
        RegisteredPatients patient = findingUsers.findThePatientByUserID(user);


        return  labBloodBankRepository.findByCompletedAndPatientIDOrderByCreationTimestampDesc(true,patient);
    }




    public Page<LabRequestProjections> getLabRequestBloodBankByPatientName(String name,Integer page, Integer size){
        return  labBloodBankRepository.findByCompletedAndPatientNameIgnoreCaseContaining(false,name,PageRequest.of(page,size));
    }




    public RequestResponse postLabBloodBankResults(TestResults results){


        Optional<LabBloodBank> testOptional = labBloodBankRepository.findById(results.getId());
        if(testOptional.isEmpty()) throw new  RuntimeException("test not found");

        LabBloodBank test = testOptional.get();

        test.setResult(results.getResult());
        test.setNotes(results.getNotes());
        test.setCompleted(results.getCompleted());

        labBloodBankRepository.save(test);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("test completed")
                .build();

    }
}
