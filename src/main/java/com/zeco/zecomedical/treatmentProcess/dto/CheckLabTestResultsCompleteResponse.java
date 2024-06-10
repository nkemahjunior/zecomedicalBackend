package com.zeco.zecomedical.treatmentProcess.dto;


import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.PatientsTable;
import com.zeco.zecomedical.general.projections.lab.ConsultationTable;
import com.zeco.zecomedical.model.Laboratories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CheckLabTestResultsCompleteResponse {

    private Long id;

    private ConsultationTable consultation;

    private String patientName;

    private PatientsTable patientID;

    private String labTestRequest;

    private Boolean result;

    private Boolean completed;

    private LocalDateTime creationTimestamp;

    private String notes;

    private Laboratories labDepartment;

}
