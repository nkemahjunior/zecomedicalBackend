package com.zeco.zecomedical.treatmentProcess.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
public class WaitingLabResultsResponse {

    private Integer status;
    private String message;
    private Long id;
    private boolean completed;
    private Boolean waiting;
    private Long patientID;
    private  Long doctorID;
    private  String labName;
    private LocalDateTime dateTime;
    private UUID consultationID;

}
