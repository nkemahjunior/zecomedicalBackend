package com.zeco.zecomedical.treatmentProcess.service;

import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.repositories.*;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.general.utils.MyDebug;
import com.zeco.zecomedical.model.*;

import com.zeco.zecomedical.patientBookAppointments.service.BookAppointmentsService;
import com.zeco.zecomedical.treatmentProcess.dto.FinishConsultationRequest;
import com.zeco.zecomedical.treatmentProcess.dto.SendToLabRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsultationService {

    private final PatientRepository patientRepository;

    private final  ConsultationRepository consultationRepository;
    private final  FindingUsers findingUsers;
    private final  LabMicrobiologyRepository labMicrobiologyRepository;
    private final LabBloodBankRepository labBloodBankRepository;
    private final  LabImmunologyRepository labImmunologyRepository;
    private final  LabParasitologyRepository labParasitologyRepository;

   private final AppointmentRequestsRepository appointmentRequestsRepository;





    public RequestResponse startConsultation(Long patientID){

        RegisteredPatients patient = RegisteredPatients.builder().id(patientID).build();
       Users user = findingUsers.findUserByTheUsername("user not found");
       Doctors doctor = findingUsers.findTheDoctorByUserID(user);

        Consultation consultation = Consultation.builder()
                .doctorID(doctor)
                .patientID(patient)
                .timestamp(LocalDateTime.now())
                .sessionFinished(false)
                .build();

        consultationRepository.save(consultation);

        return  RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Starting consultation")
                .build();
    }







    @Transactional
    public RequestResponse sendPatientToLab(SendToLabRequestDto sendToLabRequestDto){
        // bb = bloodBank, im=immunology, mb = microbiology, ps = parasitology
        Users user = findingUsers.findUserByTheUsername("user not found");
        Doctors doctor = findingUsers.findTheDoctorByUserID(user);


        Optional<RegisteredPatients> patient1 = patientRepository.findById(sendToLabRequestDto.getPatientID());
        if(patient1.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"patient not found");


        UUID key = UUID.randomUUID();
        Optional<Consultation> consultationOptional = consultationRepository.findByDoctorIDAndPatientIDAndSessionFinished(doctor,patient1.get(),false);
        if(consultationOptional.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"start a consultation session first");


        RegisteredPatients patient = RegisteredPatients.builder().id(sendToLabRequestDto.getPatientID()).build();
        LocalDateTime time = LocalDateTime.now();


        Consultation consultation = consultationOptional.get();
       switch (sendToLabRequestDto.getLabName()) {

           //TODO handle scenario where the doctor already send test requests to a specific lab and still tries to send another request to that lab
           case "bb" :
               //in the case where the doctor has already sent some request to the lab and he wants to add another again , there will be no problem with this following two lines coz
               //when we update the key,it will be updated in the lab table(UPDATE CASCADE)
               consultation.setLabResultsBloodBank(key);
               consultationRepository.save(consultation);

               Optional<Consultation> consultationID = consultationRepository.findByLabResultsBloodBank(key);
               if(consultationID.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"consultation id not found");

               Laboratories laboratoryID = Laboratories.builder().id(4L).build();



              sendToLabRequestDto.getLabTestRequest().forEach( el -> {
                  LabBloodBank bloodBankTests = LabBloodBank.builder()
                          .labResultsBloodBank(consultationID.get())
                          .doctorID(doctor)
                          .labDepartment(laboratoryID)
                          .patientName(sendToLabRequestDto.getPatientName())
                          .patientID(patient)
                          .labTestRequest(el)
                          .creationTimestamp(time)
                          .build();

                  labBloodBankRepository.save(bloodBankTests);
              });

               return  RequestResponse.builder()
                       .status(HttpStatus.CREATED.value())
                       .message("lab request sent")
                       .build();


           case "im":
               consultation.setLabResultsImmunology(key);
               consultationRepository.save(consultation);

               Optional<Consultation> consultationID2 = consultationRepository.findByLabResultsImmunology(key);
               if(consultationID2.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"consultation id not found");

               Laboratories laboratoryID2 = Laboratories.builder().id(3L).build();


               sendToLabRequestDto.getLabTestRequest().forEach( el -> {
                   LabImmunology immunologyTests = LabImmunology.builder()
                           .labResultsImmunology(consultationID2.get())
                           .doctorID(doctor)
                           .labDepartment(laboratoryID2)
                           .patientName(sendToLabRequestDto.getPatientName())
                           .patientID(patient)
                           .labTestRequest(el)
                           .creationTimestamp(time)
                           .build();

                   labImmunologyRepository.save(immunologyTests);
               });

               return  RequestResponse.builder()
                       .status(HttpStatus.CREATED.value())
                       .message("lab request sent")
                       .build();



           case "mb" :
               consultation.setLabResultsMicrobiology(key);
               consultationRepository.save(consultation);


               Optional<Consultation> consultationID3 = consultationRepository.findByLabResultsMicrobiology(key);
               if(consultationID3.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"consultation id not found");

               Laboratories laboratoryID3 = Laboratories.builder().id(1L).build();


               sendToLabRequestDto.getLabTestRequest().forEach( el -> {
                   LabMicrobiology microbiologyTests = LabMicrobiology.builder()
                           .labResultsMicrobiology(consultationID3.get())
                           .doctorID(doctor)
                           .labDepartment(laboratoryID3)
                           .patientName(sendToLabRequestDto.getPatientName())
                           .patientID(patient)
                           .labTestRequest(el)
                           .creationTimestamp(time)
                           .build();

                   labMicrobiologyRepository.save(microbiologyTests);
               });

               return  RequestResponse.builder()
                       .status(HttpStatus.CREATED.value())
                       .message("lab request sent")
                       .build();


           case "ps":
               consultation.setLabResultsParasitology(key);
               consultationRepository.save(consultation);

               Optional<Consultation> consultationID4 = consultationRepository.findByLabResultsParasitology(key);
               if(consultationID4.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"consultation id not found");

               Laboratories laboratoryID4 = Laboratories.builder().id(2L).build();


               sendToLabRequestDto.getLabTestRequest().forEach( el -> {
                   LabParasitology parasitologyTests = LabParasitology.builder()
                           .labResultsParasitology(consultationID4.get())
                           .doctorID(doctor)
                           .labDepartment(laboratoryID4)
                           .patientName(sendToLabRequestDto.getPatientName())
                           .patientID(patient)
                           .labTestRequest(el)
                           .creationTimestamp(time)
                           .build();

                   labParasitologyRepository.save(parasitologyTests);
               });

               return  RequestResponse.builder()
                       .status(HttpStatus.CREATED.value())
                       .message("lab request sent")
                       .build();

           default:
               return  RequestResponse.builder()
                       .status(HttpStatus.BAD_REQUEST.value())
                       .message("error sending lab request,check lab initials")
                       .build();
       }

    }





    public Object[] getCurrentConsultation(Long patientID){

        Users user = findingUsers.findUserByTheUsername("user not found");
        Doctors doctor = findingUsers.findTheDoctorByUserID(user);

        Optional<RegisteredPatients> patient = patientRepository.findById(patientID);
        if(patient.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"patient not found");


        Optional<Consultation> consultationOptional = consultationRepository.findByDoctorIDAndPatientIDAndSessionFinished(doctor,patient.get(),false);
        if(consultationOptional.isEmpty()) throw new MyException(HttpStatus.NOT_FOUND.value(),"start a consultation session first");


        //returning an arrray of objects
        Object[] array = {doctor,patient.get(),consultationOptional.get()};

        return array;
    }






    @Transactional
    public RequestResponse finishConsultation(FinishConsultationRequest finishConsultationRequest){

        Object[] array = getCurrentConsultation(finishConsultationRequest.getPatientID());

        if(finishConsultationRequest.getComeForCheckup())
            createAppointment(finishConsultationRequest,(Doctors) array[0],(RegisteredPatients) array[1]);


        Consultation consultation = (Consultation) array[2];
         Integer checkupYear = finishConsultationRequest.getCheckupYear();
         Integer checkupMonth = finishConsultationRequest.getCheckupMonth();
         Integer checkupDay = finishConsultationRequest.getCheckupDay();
         Integer checkupHour = finishConsultationRequest.getCheckupHour();
         Integer checkupMin = finishConsultationRequest.getCheckupMin();
         LocalDateTime checkupDate = LocalDateTime.of(checkupYear,checkupMonth,checkupDay,checkupHour,checkupMin);

         if (checkupDate.isBefore(LocalDateTime.now())) throw new MyException(HttpStatus.BAD_REQUEST.value(), "appointment date should be after today");

        consultation.setDiagnosisNotes(finishConsultationRequest.getDiagnosisNotes());
        consultation.setComeForCheckup(finishConsultationRequest.getComeForCheckup());
        consultation.setCheckupDate(checkupDate);
        consultation.setMedicinePrescribed(finishConsultationRequest.getMedicinePrescribed());

        consultation.setSessionFinished(true);
        consultationRepository.save(consultation);

        return RequestResponse.builder()
                .status(HttpStatus.OK.value())
                .message("consultation updated")
                .build();

    }





    public void createAppointment(FinishConsultationRequest data, Doctors doctor_id, RegisteredPatients patient_id){
        AppointmentRequests appointment = AppointmentRequests.builder()
                .status("PENDING")
                .reason("CHECK_UP")
                .complainNotes("coming for follow up")
                //.rende_vouz(true) checkup and rende vouz is thesame thing
                .doctorID(doctor_id)
                .patientID(patient_id)
                .dateTime(LocalDateTime.of(data.getCheckupYear(),data.getCheckupMonth(), data.getCheckupDay(), data.getCheckupHour(), data.getCheckupMin()))
                .build();

        appointmentRequestsRepository.save(appointment);
    }





    public RequestResponse deleteConsultation(Long patientID){

        Object[] array = getCurrentConsultation(patientID);
        Consultation consultation = (Consultation) array[2];

        consultationRepository.deleteById(consultation.getId());

        return RequestResponse.builder()
                .status(HttpStatus.OK.value())
                .message("consultation deleted")
                .build();
    }


}
