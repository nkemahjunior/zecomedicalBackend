package com.zeco.zecomedical.treatmentProcess.dto;

import com.zeco.zecomedical.model.RegisteredPatients;
import lombok.Data;

import java.util.List;

@Data
public class SendToLabRequestDto {

    private String labName;

    private Long patientID;

    private String patientName;

    private List<String> labTestRequest;


}
