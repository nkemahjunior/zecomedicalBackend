package com.zeco.zecomedical.treatmentProcess.dto;


import lombok.Data;

import java.util.List;

@Data
public class PauseConsultationRequest {

    private Long consultationID;
    private String diagnosisNotes;
    private String prescribedDrugs;

}
