package com.zeco.zecomedical.treatmentProcess.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;

@Data
@Builder
public class PatientInfoResponse {

    private  Long id;
    private Float weight;
    private String bloodGroup;
    private String bloodPressure;
    private String name;
    private String gender;
    private Calendar dob;
    private String profilePhotoUrl;

}
