package com.zeco.zecomedical.medicalHistory.controller;

import com.zeco.zecomedical.medicalHistory.dto.MedicalHistoryResponse;
import com.zeco.zecomedical.medicalHistory.service.MedicalHistoryService;
import com.zeco.zecomedical.model.Consultation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicalHistory")
@RequiredArgsConstructor
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    @GetMapping("/{patientID}")
    public ResponseEntity<List<MedicalHistoryResponse>> getAPatientMedicalHistoryEndpoint(@PathVariable Long patientID){
        return ResponseEntity.ok(medicalHistoryService.getAPatientMedicalHistory(patientID));
    }

}
