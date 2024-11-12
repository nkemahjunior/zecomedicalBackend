package com.zeco.zecomedical.treatmentProcess.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class WaitingLabResultsRequest {

    private  String labName;

    private Long patientID;

    UUID consultationID;


}
