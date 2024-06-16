package com.zeco.zecomedical.laboratory.controller;


import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.laboratory.dto.TestResults;
import com.zeco.zecomedical.laboratory.service.*;
import com.zeco.zecomedical.model.LabTechnicians;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/lab")
public class LaboratoryController {

    private final LaboratoryService laboratoryService;
    private final LabMicrobiologyService labMicrobiologyService;
    private final LabBloodBankService labBloodBankService;
    private final LabParasitologyService labParasitologyService;
    private  final LabImmunologyService labImmunologyService;


    @GetMapping("/department")
    public ResponseEntity<LabTechnicians> getLabDepartment(){
        return  ResponseEntity.ok(laboratoryService.getLabDepartment());
    }

    @GetMapping("/microbiology/request")//request?page=0&size=5
    public ResponseEntity<Page<LabRequestProjections>> getLabMicrobiologyRequests(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labMicrobiologyService.getLabRequestMicrobiology(page,size));
    }

    @GetMapping("/microbiology/request/{patientName}")
    public ResponseEntity<Page<LabRequestProjections>> getLabMicrobiologyRequestsByName(@PathVariable("patientName") String patientName,
                                                                                        @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labMicrobiologyService.getLabRequestMicrobiologyByPatientName(patientName,page,size));
    }

    @PostMapping("/microbiology/results")
    public ResponseEntity<RequestResponse> postLabTestResultMicrobiologyEndpoint(@RequestBody TestResults result){
        return  ResponseEntity.ok(labMicrobiologyService.postLabMicrobiologyResults(result));
    }



    @GetMapping("/bloodBank/request")//request?page=0&size=5
    public ResponseEntity<Page<LabRequestProjections>> getLabBloodBankRequests(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labBloodBankService.getLabRequesBloodBank(page,size));
    }

    @GetMapping("/bloodBank/request/{patientName}")// /request/nkemah?page=0&size10
    public ResponseEntity<Page<LabRequestProjections>> getLabBloodBankRequestsByName(@PathVariable("patientName") String patientName,
                                                                                     @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labBloodBankService.getLabRequestBloodBankByPatientName(patientName,page,size));
    }


    //only the patient is using this api
    @GetMapping("/bloodBank/results/patient")
    public ResponseEntity<List<LabRequestProjections>> getLabBloodBankRequestsPatient(){
        return  ResponseEntity.ok(labBloodBankService.getLabRequestBloodBankPatient());
    }

    @PostMapping("/bloodBank/results")
    public ResponseEntity<RequestResponse> postLabTestResultBloodBankEndpoint(@RequestBody TestResults result){
        return  ResponseEntity.ok(labBloodBankService.postLabBloodBankResults(result));
    }




    @GetMapping("/immunology/request")//request?page=0&size=5
    public ResponseEntity<Page<LabRequestProjections>> getLabImmunologyRequests(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labImmunologyService.getLabRequesImmunology(page,size));
    }

    @GetMapping("/immunology/request/{patientName}")
    public ResponseEntity<Page<LabRequestProjections>> getLabImmunologyRequestsByName(@PathVariable("patientName") String patientName,
                                                                                      @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labImmunologyService.getLabRequestImmunologyByPatientName(patientName,page,size));
    }

    @PostMapping("/immunology/results")
    public ResponseEntity<RequestResponse> postLabTestResultImmunologyEndpoint(@RequestBody TestResults result){
        return  ResponseEntity.ok(labImmunologyService.postLabImmunologyResults(result));
    }





    @GetMapping("/parasitology/request")//request?page=0&size=5
    public ResponseEntity<Page<LabRequestProjections>> getLabParasitologyRequests(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labParasitologyService.getLabRequesParasitology(page,size));
    }

    @GetMapping("/parasitology/request/{patientName}")
    public ResponseEntity<Page<LabRequestProjections>> getLabParasitologyRequestsByName(@PathVariable("patientName") String patientName,
                                                                                        @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return  ResponseEntity.ok(labParasitologyService.getLabRequestParasitologyByPatientName(patientName,page,size));
    }

    @PostMapping("/parasitology/results")
    public ResponseEntity<RequestResponse> postLabTestResultParasitologyEndpoint(@RequestBody TestResults result){
        return  ResponseEntity.ok(labParasitologyService.postLabParasitologyResults(result));
    }
}
