package com.zeco.zecomedical.laboratory.service;


import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.general.repositories.LabMicrobiologyRepository;
import com.zeco.zecomedical.laboratory.dto.TestResults;
import com.zeco.zecomedical.model.LabMicrobiology;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class LabMicrobiologyService {

    private  final LabMicrobiologyRepository labMicrobiologyRepository;

    public Page<LabRequestProjections> getLabRequestMicrobiology(Integer page, Integer size){
        return  labMicrobiologyRepository.findByCompleted(false, PageRequest.of(page, size));
    }



    public Page<LabRequestProjections> getLabRequestMicrobiologyByPatientName(String name,Integer page, Integer size){
        return  labMicrobiologyRepository.findByCompletedAndPatientNameIgnoreCaseContaining(false,name,PageRequest.of(page,size));
    }



    public RequestResponse postLabMicrobiologyResults(TestResults results){

        Optional<LabMicrobiology> testOptional = labMicrobiologyRepository.findById(results.getId());
        if(testOptional.isEmpty()) throw new  RuntimeException("test not found");

        LabMicrobiology test = testOptional.get();

        test.setResult(results.getResult());
        test.setNotes(results.getNotes());
        test.setCompleted(results.getCompleted());

        labMicrobiologyRepository.save(test);

        return RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("test completed")
                .build();

    }
}
