package com.zeco.zecomedical.laboratory.service;


import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.general.repositories.LabImmunologyRepository;
import com.zeco.zecomedical.laboratory.dto.TestResults;
import com.zeco.zecomedical.model.LabImmunology;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LabImmunologyService {

    private final LabImmunologyRepository labImmunologyRepository;


    public Page<LabRequestProjections> getLabRequesImmunology(Integer page, Integer size){
        return  labImmunologyRepository.findByCompleted(false, PageRequest.of(page,size));
    }


    public Page<LabRequestProjections> getLabRequestImmunologyByPatientName(String name,Integer page, Integer size){
        return  labImmunologyRepository.findByCompletedAndPatientNameIgnoreCaseContaining(false,name,PageRequest.of(page,size));
    }

    public RequestResponse postLabImmunologyResults(TestResults results){

        Optional<LabImmunology> testOptional = labImmunologyRepository.findById(results.getId());
        if(testOptional.isEmpty()) throw new  RuntimeException("test not found");

        LabImmunology test = testOptional.get();

        test.setResult(results.getResult());
        test.setNotes(results.getNotes());
        test.setCompleted(results.getCompleted());

        labImmunologyRepository.save(test);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("test completed")
                .build();

    }
}
