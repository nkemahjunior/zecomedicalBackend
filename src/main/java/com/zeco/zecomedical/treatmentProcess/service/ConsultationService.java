package com.zeco.zecomedical.treatmentProcess.service;

import com.zeco.zecomedical.customExceptions.MyException;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.general.projections.consultations.ConsultationsProjection;
import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.PatientsTable;
import com.zeco.zecomedical.general.repositories.*;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.general.utils.MyDebug;
import com.zeco.zecomedical.model.*;

import com.zeco.zecomedical.patientBookAppointments.service.BookAppointmentsService;
import com.zeco.zecomedical.treatmentProcess.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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


   public PatientInfoResponse getPatientInfo(Long id){
      Optional<RegisteredPatients> patientOptional =  patientRepository.findById(id);
      if(patientOptional.isEmpty()) throw  new RuntimeException("patient not found");
      RegisteredPatients patient = patientOptional.get();

      return PatientInfoResponse.builder()
              .id(patient.getId())
              .name(patient.getPatientID().getName())
              .gender(patient.getPatientID().getGender())
              .dob(patient.getPatientID().getDob())
              .profilePhotoUrl(patient.getPatientID().getProfilePhotoUrl())
              .bloodPressure(patient.getBloodPressure())
              .bloodGroup(patient.getBloodGroup())
              .weight(patient.getWeight())
              .build();
   }




    public StartConsultationResponse startConsultation(Long patientID){

        RegisteredPatients patient = RegisteredPatients.builder().id(patientID).build();
       Users user = findingUsers.findUserByTheUsername("user not found");
       Doctors doctor = findingUsers.findTheDoctorByUserID(user);

        Consultation consultation = Consultation.builder()
                .doctorID(doctor)
                .patientID(patient)
                .timestamp(LocalDateTime.now())
                .sessionFinished(false)
                .build();

        Consultation savedConsultation = consultationRepository.save(consultation);

        return  StartConsultationResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Starting consultation")
                .consultationID(savedConsultation.getId())
                .build();
    }







    @Transactional
    public SendToLabResponse sendPatientToLab(SendToLabRequestDto sendToLabRequestDto){
        // bb = bloodBank, im=immunology, mb = microbiology, ps = parasitology
        Users user = findingUsers.findUserByTheUsername("user not found");


        MyDebug.printBlock();
        log.error(user);
        MyDebug.printBlock();

        Doctors doctor = new Doctors();
        doctor.setDoctor_id(7L);

        Optional<RegisteredPatients> patient1 = patientRepository.findById(sendToLabRequestDto.getPatientID());

        UUID key = UUID.randomUUID();
        Optional<Consultation> consultationOptional = consultationRepository.findByDoctorIDAndPatientIDAndSessionFinished(doctor,patient1.get(),false);

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
                          .completed(false)
                          .creationTimestamp(time)
                          .build();

                  labBloodBankRepository.save(bloodBankTests);
              });

               return  SendToLabResponse.builder()
                       .status(HttpStatus.CREATED.value())
                       .message("lab request sent")
                       .consultationID(key)
                       .build();










           default:
               return  SendToLabResponse.builder()
                       .status(HttpStatus.BAD_REQUEST.value())
                       .message("error sending lab request,check lab initials")
                       .consultationID(null)
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




    public ConsultationResponse pauseConsultation(PauseConsultationRequest request){
        Optional<Consultation> consultationOpt = consultationRepository.findById(request.getConsultationID());
        if(consultationOpt.isEmpty()) throw new RuntimeException("consultation not found");

        Consultation consultation = consultationOpt.get();
        consultation.setDiagnosisNotes(request.getDiagnosisNotes());
        consultation.setMedicinePrescribed(request.getPrescribedDrugs());
        consultation.setStatus("PAUSED");

        Consultation pausedConsultation = consultationRepository.save(consultation);


        return ConsultationResponse.builder()
                .status(HttpStatus.OK.value())
                .message("consultation session paused, you can attend to other patients")
                .id(pausedConsultation.getId())
                .patientID(pausedConsultation.getPatientID().getId())
                .patientName(pausedConsultation.getPatientID().getPatientID().getName())
                .diagnosisNotes(pausedConsultation.getDiagnosisNotes())
                .labResultsBloodBank(pausedConsultation.getLabResultsBloodBank())
                .labResultsParasitology(pausedConsultation.getLabResultsParasitology())
                .labResultsMicrobiology(pausedConsultation.getLabResultsParasitology())
                .labResultsImmunology(pausedConsultation.getLabResultsImmunology())
                .medicinePrescribed(pausedConsultation.getMedicinePrescribed())
                .build();
    }


    public List<ConsultationResponse> getPausedConsultations(){

        Users user = findingUsers.findUserByTheUsername("user not found");
        Doctors doctor = findingUsers.findTheDoctorByUserID(user);

        List<ConsultationsProjection> pausedConsultations = consultationRepository.findByDoctorIDAndSessionFinishedAndStatus(doctor,false,"PAUSED");

        return  pausedConsultations.stream().map(el -> ConsultationResponse.builder()
                .id(el.getId())
                //.patientID(el.getPatientID())
                .patientID(el.getPatientID().getId())
                .patientName(el.getPatientID().getPatientID().getName())
                .diagnosisNotes(el.getDiagnosisNotes())
                .labResultsBloodBank(el.getLabResultsBloodBank())
                .labResultsParasitology(el.getLabResultsParasitology())
                .labResultsMicrobiology(el.getLabResultsParasitology())
                .labResultsImmunology(el.getLabResultsImmunology())
                .medicinePrescribed(el.getMedicinePrescribed())
                .build()

        ).toList();
    }






    @Transactional
    public RequestResponse finishConsultation(FinishConsultationRequest finishConsultationRequest){

        Object[] array = getCurrentConsultation(finishConsultationRequest.getPatientID());

        Integer checkupYear = null;
        Integer checkupMonth = null;
        Integer checkupDay = null;
        Integer checkupHour = null;
        Integer checkupMin = null;
        LocalDateTime checkupDate = null;


        if(finishConsultationRequest.getComeForCheckup()){

            checkupYear = finishConsultationRequest.getCheckupYear();
            checkupMonth = finishConsultationRequest.getCheckupMonth();
            checkupDay = finishConsultationRequest.getCheckupDay();
            checkupHour = finishConsultationRequest.getCheckupHour();
            checkupMin = finishConsultationRequest.getCheckupMin();
            checkupDate = LocalDateTime.of(checkupYear,checkupMonth,checkupDay,checkupHour,checkupMin);


            if (checkupDate.isBefore(LocalDateTime.now())) throw new MyException(HttpStatus.BAD_REQUEST.value(), "appointment date should be after today");


            createAppointment(checkupDate, (Doctors) array[0], (RegisteredPatients) array[1]);
        }



        Consultation consultation = (Consultation) array[2];

        consultation.setDiagnosisNotes(finishConsultationRequest.getDiagnosisNotes());
        consultation.setComeForCheckup(finishConsultationRequest.getComeForCheckup());
        consultation.setCheckupDate(checkupDate);
        consultation.setMedicinePrescribed(finishConsultationRequest.getMedicinePrescribed());
        consultation.setStatus("FINISHED");

        consultation.setSessionFinished(true);
        consultationRepository.save(consultation);

        return RequestResponse.builder()
                .status(HttpStatus.OK.value())
                .message("consultation finished")
                .build();

    }





    public void createAppointment(LocalDateTime checkUpDate, Doctors doctor_id, RegisteredPatients patient_id){
        AppointmentRequests appointment = AppointmentRequests.builder()
                .status("PENDING")
                .reason("checkup")
                .complainNotes("coming for follow up")
                //.rende_vouz(true) checkup and rende vouz is thesame thing
                .doctorID(doctor_id)
                .patientID(patient_id)
                .dateTime(checkUpDate)
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
