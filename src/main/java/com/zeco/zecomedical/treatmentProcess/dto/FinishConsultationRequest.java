package com.zeco.zecomedical.treatmentProcess.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class FinishConsultationRequest {

    private Long patientID;

    private String diagnosisNotes;

    private Boolean comeForCheckup;

    private Integer checkupYear;

    private Integer checkupMonth;

    private Integer checkupDay;

    private Integer checkupHour;

    private Integer checkupMin;

    private String medicinePrescribed;

    //private Boolean sessionFinished;
}
