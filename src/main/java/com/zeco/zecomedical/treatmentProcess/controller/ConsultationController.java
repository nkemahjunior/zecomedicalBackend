package com.zeco.zecomedical.treatmentProcess.controller;


import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.treatmentProcess.dto.FinishConsultationRequest;
import com.zeco.zecomedical.treatmentProcess.dto.SendToLabRequestDto;
import com.zeco.zecomedical.treatmentProcess.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;



    @PostMapping("/start/{patientID}")
    public ResponseEntity<RequestResponse> startConsultationEndpoint(@PathVariable Long patientID){

        return ResponseEntity.ok(consultationService.startConsultation(patientID));
    }

    @PostMapping("/sendToLab")
    public ResponseEntity<RequestResponse> sendToLAbEndpoint(@RequestBody SendToLabRequestDto sendToLabRequestDto){
        return ResponseEntity.ok(consultationService.sendPatientToLab(sendToLabRequestDto));
    }

    @PutMapping("/finish")
    public ResponseEntity<RequestResponse> finishConsultationEndpoint(@RequestBody FinishConsultationRequest finishConsultationRequest){
        return ResponseEntity.ok(consultationService.finishConsultation(finishConsultationRequest));
    }

    @DeleteMapping("/{patientID}")
    public ResponseEntity<RequestResponse> deleteConsultation(@PathVariable Long patientID){
        return ResponseEntity.ok(consultationService.deleteConsultation(patientID));
    }

}
