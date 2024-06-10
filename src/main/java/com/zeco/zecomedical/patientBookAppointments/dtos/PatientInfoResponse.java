package com.zeco.zecomedical.patientBookAppointments.dtos;




import lombok.Builder;
import lombok.Data;


import java.util.Calendar;


@Data
@Builder
public class PatientInfoResponse {

    //private Long id;

    private Float weight;

    private String bloodGroup;
    private String bloodPressure;
    private String email;

    private String name;


    private String username;

    private String gender;

    private Calendar dob;

    private String address;

    //private String password;

    private String profilePhotoUrl;

    //private Roles role;

   // private Boolean isAuthenticated;

   // private Boolean verified;


}
