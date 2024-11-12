package com.zeco.zecomedical.medicalHistory.service;

import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.general.projections.consultations.ConsultationsProjection;
import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.general.repositories.*;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.medicalHistory.dto.MedicalHistoryResponse;
import com.zeco.zecomedical.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalHistoryService {

    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private  final LabBloodBankRepository labBloodBankRepository;
    private final LabMicrobiologyRepository labMicrobiologyRepository;
    private final LabImmunologyRepository labImmunologyRepository;
    private final LabParasitologyRepository labParasitologyRepository;
    private final FindingUsers findingUsers;



    public List<MedicalHistoryResponse> getAPatientMedicalHistoryDoctor(Long patientID){

        Optional<RegisteredPatients> patientOptional = patientRepository.findById(patientID);
        if(patientOptional.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(), "patient not found");

        List<MedicalHistoryResponse> medicalHistoryResponseList = new ArrayList<>();

       List<ConsultationsProjection> consultationHistories = consultationRepository.findByPatientIDAndSessionFinishedOrderByTimestampDesc(patientOptional.get(),true);
       for(ConsultationsProjection el : consultationHistories){


           MedicalHistoryResponse medHistory = new MedicalHistoryResponse();
           medHistory.setId(el.getId());
           medHistory.setDoctorID(el.getDoctorID());
           //medHistory.setPatientID(el.getPatientID());
           medHistory.setDiagnosisNotes(el.getDiagnosisNotes());
           medHistory.setTimestamp(el.getTimestamp());
           medHistory.setComeForCheckup(el.getComeForCheckup());
           medHistory.setCheckupDate(el.getCheckupDate());
           medHistory.setSessionFinished(el.getSessionFinished());
           medHistory.setMedicinePrescribed(el.getMedicinePrescribed());



           if(el.getLabResultsBloodBank() != null){
               Optional<Consultation> con = consultationRepository.findByLabResultsBloodBank(el.getLabResultsBloodBank());
               List<LabRequestProjections> bloodBankTests = labBloodBankRepository.findByLabResultsBloodBank(con.get());

               medHistory.setLabResultsBloodBank(bloodBankTests);
           }

           if(el.getLabResultsMicrobiology() != null){
               Optional<Consultation> con1 = consultationRepository.findByLabResultsMicrobiology(el.getLabResultsMicrobiology());
               List<LabRequestProjections> microbiologyTests = labMicrobiologyRepository.findByLabResultsMicrobiology(con1.get());

               medHistory.setLabResultsMicrobiology(microbiologyTests);
           }

           if(el.getLabResultsImmunology() != null){
               Optional<Consultation> con2 = consultationRepository.findByLabResultsImmunology(el.getLabResultsImmunology());
               List<LabRequestProjections> immunologyTests =  labImmunologyRepository.findByLabResultsImmunology(con2.get());

               medHistory.setLabResultsImmunology(immunologyTests);
           }

           if(el.getLabResultsParasitology() != null){
               Optional<Consultation> con3 = consultationRepository.findByLabResultsParasitology(el.getLabResultsParasitology());
               List<LabRequestProjections> parasitologyTests =  labParasitologyRepository.findByLabResultsParasitology(con3.get());

               medHistory.setLabResultsParasitology(parasitologyTests);
           }

           medicalHistoryResponseList.add(medHistory);

       }

       return medicalHistoryResponseList;

    }


    public List<MedicalHistoryResponse> getMedicalHistoryPatient(){

        Users user = findingUsers.findUserByTheUsername("user not found");
        RegisteredPatients patient = findingUsers.findThePatientByUserID(user);


        List<MedicalHistoryResponse> medicalHistoryResponseList = new ArrayList<>();

        List<ConsultationsProjection> consultationHistories = consultationRepository.findByPatientIDAndSessionFinishedOrderByTimestampDesc(patient,true);
        for(ConsultationsProjection el : consultationHistories){


            MedicalHistoryResponse medHistory = new MedicalHistoryResponse();
            medHistory.setId(el.getId());
            medHistory.setDoctorID(el.getDoctorID());
            //medHistory.setPatientID(el.getPatientID());
            medHistory.setDiagnosisNotes(el.getDiagnosisNotes());
            medHistory.setTimestamp(el.getTimestamp());
            medHistory.setComeForCheckup(el.getComeForCheckup());
            medHistory.setCheckupDate(el.getCheckupDate());
            medHistory.setSessionFinished(el.getSessionFinished());
            medHistory.setMedicinePrescribed(el.getMedicinePrescribed());



            if(el.getLabResultsBloodBank() != null){
                Optional<Consultation> con = consultationRepository.findByLabResultsBloodBank(el.getLabResultsBloodBank());
                List<LabRequestProjections> bloodBankTests = labBloodBankRepository.findByLabResultsBloodBank(con.get());

                medHistory.setLabResultsBloodBank(bloodBankTests);
            }

            if(el.getLabResultsMicrobiology() != null){
                Optional<Consultation> con1 = consultationRepository.findByLabResultsMicrobiology(el.getLabResultsMicrobiology());
                List<LabRequestProjections> microbiologyTests = labMicrobiologyRepository.findByLabResultsMicrobiology(con1.get());

                medHistory.setLabResultsMicrobiology(microbiologyTests);
            }

            if(el.getLabResultsImmunology() != null){
                Optional<Consultation> con2 = consultationRepository.findByLabResultsImmunology(el.getLabResultsImmunology());
                List<LabRequestProjections> immunologyTests =  labImmunologyRepository.findByLabResultsImmunology(con2.get());

                medHistory.setLabResultsImmunology(immunologyTests);
            }

            if(el.getLabResultsParasitology() != null){
                Optional<Consultation> con3 = consultationRepository.findByLabResultsParasitology(el.getLabResultsParasitology());
                List<LabRequestProjections> parasitologyTests =  labParasitologyRepository.findByLabResultsParasitology(con3.get());

                medHistory.setLabResultsParasitology(parasitologyTests);
            }

            medicalHistoryResponseList.add(medHistory);

        }

        return medicalHistoryResponseList;



    }
}
