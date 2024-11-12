package com.zeco.zecomedical.activateAccounts.activationDtos;

import com.zeco.zecomedical.model.Users;
import lombok.Data;

@Data
public class ActivatePatientAccountRequest {


    //private Long id;
    private Float weight;
    private String bloodGroup;
    private String bloodPressure;
   // private Users patientID;
    //private String email;
}
