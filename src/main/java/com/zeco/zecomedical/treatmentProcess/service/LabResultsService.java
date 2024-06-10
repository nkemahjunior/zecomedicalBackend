package com.zeco.zecomedical.treatmentProcess.service;


import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.PatientsTable;
import com.zeco.zecomedical.general.projections.lab.*;
import com.zeco.zecomedical.general.repositories.LabBloodBankRepository;
import com.zeco.zecomedical.general.repositories.LabImmunologyRepository;
import com.zeco.zecomedical.general.repositories.LabMicrobiologyRepository;
import com.zeco.zecomedical.general.repositories.LabParasitologyRepository;
import com.zeco.zecomedical.general.utils.MyDebug;
import com.zeco.zecomedical.model.Consultation;
import com.zeco.zecomedical.model.LabMicrobiology;
import com.zeco.zecomedical.treatmentProcess.dto.CheckLabTestResultsCompleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class LabResultsService {

    private final LabMicrobiologyRepository microbiologyRepository;
    private final LabParasitologyRepository parasitologyRepository;
    private final LabImmunologyRepository labImmunologyRepository;
    private  final LabBloodBankRepository labBloodBankRepository;

    public List<CheckLabTestResultsCompleteResponse> getLabMicrobiologyResults(String consultationLabID){

        UUID id = UUID.fromString(consultationLabID);


        Consultation consultation = new Consultation();
        consultation.setLabResultsMicrobiology(id);

        List<LabMicrobiologyProjection > result =  microbiologyRepository.findByLabResultsMicrobiologyAndCompleted(consultation,true);


        return  result.stream().map(el ->
            CheckLabTestResultsCompleteResponse.builder()
                    .id(el.getId())
                    .consultation(el.getLabResultsMicrobiology())
                    .patientName(el.getPatientName())
                    .patientID(el.getPatientID())
                    .labTestRequest(el.getLabTestRequest())
                    .result(el.getResult())
                    .completed(el.getCompleted())
                    .creationTimestamp(el.getCreationTimestamp())
                    .notes(el.getNotes())
                    .labDepartment(el.getLabDepartment())
                    .build()
        ).toList();
    }




    public List<CheckLabTestResultsCompleteResponse> getLabParasitologyResults(String consultationLabID){

        UUID id = UUID.fromString(consultationLabID);

        Consultation consultation = new Consultation();
        consultation.setLabResultsParasitology(id);

        List<LabParasitologyProjection > result =  parasitologyRepository.findByLabResultsParasitologyAndCompleted(consultation,true);


        return  result.stream().map(el ->
            CheckLabTestResultsCompleteResponse.builder()
                    .id(el.getId())
                    .consultation(el.getLabResultsParasitology())
                    .patientName(el.getPatientName())
                    .patientID(el.getPatientID())
                    .labTestRequest(el.getLabTestRequest())
                    .result(el.getResult())
                    .completed(el.getCompleted())
                    .creationTimestamp(el.getCreationTimestamp())
                    .notes(el.getNotes())
                    .labDepartment(el.getLabDepartment())
                    .build()
        ).toList();
    }



    public List<CheckLabTestResultsCompleteResponse> getLabImmunologyResults(String consultationLabID){

        UUID id = UUID.fromString(consultationLabID);

        Consultation consultation = new Consultation();
        consultation.setLabResultsImmunology(id);

        List<LabImmunologyProjection> result =  labImmunologyRepository.findByLabResultsImmunologyAndCompleted(consultation,true);


        return  result.stream().map(el ->
             CheckLabTestResultsCompleteResponse.builder()
                    .id(el.getId())
                    .consultation(el.getLabResultsImmunology())
                    .patientName(el.getPatientName())
                    .patientID(el.getPatientID())
                    .labTestRequest(el.getLabTestRequest())
                    .result(el.getResult())
                    .completed(el.getCompleted())
                    .creationTimestamp(el.getCreationTimestamp())
                    .notes(el.getNotes())
                     .labDepartment(el.getLabDepartment())
                    .build() ).toList();

    }


    public List<CheckLabTestResultsCompleteResponse> getLabBloodBankResults(String consultationLabID){

        UUID id = UUID.fromString(consultationLabID);

        Consultation consultation = new Consultation();
        consultation.setLabResultsBloodBank(id);

        List<LabBloodBankProjection> result =  labBloodBankRepository.findByLabResultsBloodBankAndCompleted(consultation,true);


        return  result.stream().map(el -> CheckLabTestResultsCompleteResponse.builder()
               .id(el.getId())
               .consultation(el.getLabResultsBloodBank())
               .patientName(el.getPatientName())
               .patientID(el.getPatientID())
               .labTestRequest(el.getLabTestRequest())
               .result(el.getResult())
               .completed(el.getCompleted())
               .creationTimestamp(el.getCreationTimestamp())
               .notes(el.getNotes())
                .labDepartment(el.getLabDepartment())
               .build()).toList();
    }
}
