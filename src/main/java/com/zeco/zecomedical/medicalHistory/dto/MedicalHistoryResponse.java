package com.zeco.zecomedical.medicalHistory.dto;

import com.zeco.zecomedical.general.projections.lab.LabRequestProjections;
import com.zeco.zecomedical.general.projections.patient.doctorsAvailable.DoctorsTable;
import com.zeco.zecomedical.model.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryResponse {

    private Long id;

    private DoctorsTable doctorID;

    private RegisteredPatients patientID;

    private String diagnosisNotes;

    private LocalDateTime timestamp;

    private Boolean comeForCheckup;

    private LocalDateTime checkupDate;

    private List<LabRequestProjections> labResultsBloodBank;

    private List<LabRequestProjections> labResultsImmunology;

    private List<LabRequestProjections> labResultsMicrobiology;

    private List<LabRequestProjections> labResultsParasitology;

    private Boolean sessionFinished;

    private String medicinePrescribed;
}
