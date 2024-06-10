package com.zeco.zecomedical.treatmentProcess.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StartConsultationResponse {

    private Integer status;
    private String message;
    private  Long consultationID;
}
