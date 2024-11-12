package com.zeco.zecomedical.laboratory.service;


import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.general.repositories.LabParasitologyRepository;
import com.zeco.zecomedical.laboratory.dto.TestResults;
import com.zeco.zecomedical.model.LabBloodBank;
import com.zeco.zecomedical.model.LabParasitology;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LabParasitologyService {

    private final LabParasitologyRepository labParasitologyRepository;


    public Page<LabRequestProjections> getLabRequesParasitology(Integer page, Integer size){
        return  labParasitologyRepository.findByCompleted(false, PageRequest.of(page,size));
    }

    public Page<LabRequestProjections> getLabRequestParasitologyByPatientName(String name,Integer page, Integer size){
        return  labParasitologyRepository.findByCompletedAndPatientNameIgnoreCaseContaining(false,name,PageRequest.of(page,size));
    }

    public RequestResponse postLabParasitologyResults(TestResults results){

        Optional<LabParasitology> testOptional = labParasitologyRepository.findById(results.getId());
        if(testOptional.isEmpty()) throw new  RuntimeException("test not found");

        LabParasitology test = testOptional.get();

        test.setResult(results.getResult());
        test.setNotes(results.getNotes());
        test.setCompleted(results.getCompleted());

        labParasitologyRepository.save(test);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("test completed")
                .build();

    }
}
