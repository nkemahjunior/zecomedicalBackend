package com.zeco.zecomedical.treatmentProcess.controller;


import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.consultations.ConsultationsProjection;
import com.zeco.zecomedical.general.projections.lab.LabMicrobiologyProjection;
import com.zeco.zecomedical.model.Consultation;
import com.zeco.zecomedical.model.LabMicrobiology;
import com.zeco.zecomedical.model.RegisteredPatients;
import com.zeco.zecomedical.treatmentProcess.dto.*;
import com.zeco.zecomedical.treatmentProcess.service.ConsultationService;
import com.zeco.zecomedical.treatmentProcess.service.LabResultsService;
import com.zeco.zecomedical.treatmentProcess.service.WaitingLabResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;
    private final WaitingLabResultsService waitingLabResultsService;
    private final LabResultsService labResultsService;

    @GetMapping("/patientInfo/{id}")
    public ResponseEntity<PatientInfoResponse> getPatientInfoEndpoint(@PathVariable Long id){
        return ResponseEntity.ok(consultationService.getPatientInfo(id));
    }


    @PostMapping("/start/{patientID}")
    public ResponseEntity<StartConsultationResponse> startConsultationEndpoint(@PathVariable Long patientID){

        return ResponseEntity.ok(consultationService.startConsultation(patientID));
    }

    @PostMapping("/sendToLab")
    public ResponseEntity<SendToLabResponse> sendToLAbEndpoint(@RequestBody SendToLabRequestDto sendToLabRequestDto){
        return ResponseEntity.ok(consultationService.sendPatientToLab(sendToLabRequestDto));
    }

    @PutMapping("/pause")
    public ResponseEntity<ConsultationResponse> pauseConsultationSession(@RequestBody PauseConsultationRequest consultationRequest){
        return  ResponseEntity.ok(consultationService.pauseConsultation(consultationRequest));
    }

    @GetMapping("/resume")
    public ResponseEntity<List<ConsultationResponse>> getPausedConsultations(){
        return  ResponseEntity.ok(consultationService.getPausedConsultations());
    }

    @PutMapping("/finish")
    public ResponseEntity<RequestResponse> finishConsultationEndpoint(@RequestBody FinishConsultationRequest finishConsultationRequest){
        return ResponseEntity.ok(consultationService.finishConsultation(finishConsultationRequest));
    }

    @DeleteMapping("/{patientID}")
    public ResponseEntity<RequestResponse> deleteConsultation(@PathVariable Long patientID){
        return ResponseEntity.ok(consultationService.deleteConsultation(patientID));
    }



    @GetMapping("/waitingResult")
    public ResponseEntity<List<WaitingLabResultsResponse>> getWaitingLabResultEndpoint(){
        return  ResponseEntity.ok(waitingLabResultsService.getPendingLabRequest());
    }

    @PostMapping("/waitingLabResult/save")
    public ResponseEntity<WaitingLabResultsResponse> saveWaitingLabResultEndpoint(@RequestBody WaitingLabResultsRequest request){
        return ResponseEntity.ok(waitingLabResultsService.savePendingLabRequest(request));
    }

    //waitingLabResult/complete/4d7f7de4-11f8-402c-85e1-a08b70593d43

    @PutMapping("/waitingLabResult/complete/{consultationID}")
    public  ResponseEntity<RequestResponse> updateWaitingLabResult(@PathVariable("consultationID") String consultationID){
        return ResponseEntity.ok(waitingLabResultsService.markWaitingResultAsComplete(consultationID));
    }



    @GetMapping("/labMicrobiology/results/{consultationLabId}")
    public ResponseEntity<List<CheckLabTestResultsCompleteResponse>> getLabMicrobiologyTestResults(@PathVariable("consultationLabId") String consultationLabId){
        return ResponseEntity.ok(labResultsService.getLabMicrobiologyResults(consultationLabId));
    }

    @GetMapping("/labBloodBank/results/{consultationLabId}")
    public ResponseEntity<List<CheckLabTestResultsCompleteResponse>> getLabBloodBankTestResults(@PathVariable("consultationLabId") String consultationLabId){
        return ResponseEntity.ok(labResultsService.getLabBloodBankResults(consultationLabId));
    }


    @GetMapping("/labImmunology/results/{consultationLabId}")
    public ResponseEntity<List<CheckLabTestResultsCompleteResponse>> getLabImmunologyTestResults(@PathVariable("consultationLabId") String consultationLabId){
        return ResponseEntity.ok(labResultsService.getLabImmunologyResults(consultationLabId));
    }


    @GetMapping("/labParasitology/results/{consultationLabId}")
    public ResponseEntity<List<CheckLabTestResultsCompleteResponse>> getLabParasitologyTestResults(@PathVariable("consultationLabId") String consultationLabId){
        return ResponseEntity.ok(labResultsService.getLabParasitologyResults(consultationLabId));
    }


}
