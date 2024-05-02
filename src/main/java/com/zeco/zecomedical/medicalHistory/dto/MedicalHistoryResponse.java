package com.zeco.zecomedical.medicalHistory.dto;

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

    private Doctors doctorID;

    private RegisteredPatients patientID;

    private String diagnosisNotes;

    private LocalDateTime timestamp;

    private Boolean comeForCheckup;

    private LocalDateTime checkupDate;

    private List<LabBloodBank> labResultsBloodBank;

    private List<LabImmunology> labResultsImmunology;

    private List<LabMicrobiology> labResultsMicrobiology;

    private List<LabParasitology> labResultsParasitology;

    private Boolean sessionFinished;

    private List<String> medicinePrescribed;
}
