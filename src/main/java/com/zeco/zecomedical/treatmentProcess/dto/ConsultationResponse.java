package com.zeco.zecomedical.treatmentProcess.dto;

import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.PatientsTable;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.RegisteredPatients;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class ConsultationResponse {

    private Integer status;

    private  String message;

    private Long id;

   // private Doctors doctorID;

    //private PatientsTable patientID;

    private  Long patientID;

    private String patientName;

    private String diagnosisNotes;

    //private LocalDateTime timestamp;

    //private Boolean comeForCheckup;

   // private LocalDateTime checkupDate;

    private UUID labResultsBloodBank;

    private UUID labResultsImmunology;

    private UUID labResultsMicrobiology;

    private UUID labResultsParasitology;

   // private Boolean sessionFinished;

    private String medicinePrescribed;

   // private String status;

}
