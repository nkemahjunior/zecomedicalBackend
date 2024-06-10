package com.zeco.zecomedical.treatmentProcess.dto;


import com.zeco.zecomedical.model.Consultation;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.Laboratories;
import com.zeco.zecomedical.model.RegisteredPatients;

import java.time.LocalDateTime;

public class LabResultsResponse {


    private Long id;



    private Consultation consultationLabID;

    private Doctors doctorID;

    private Laboratories labDepartment;

    private String patientName;

    private RegisteredPatients patientID;


    private String labTestRequest;

    private Boolean result;

    private Boolean completed;

    private LocalDateTime creationTimestamp;

    private String notes;
}
