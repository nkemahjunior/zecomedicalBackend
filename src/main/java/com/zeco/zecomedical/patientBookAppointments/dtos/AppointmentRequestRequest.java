package com.zeco.zecomedical.patientBookAppointments.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestRequest {

    //private String status;

    private String reason;

    private  String complain_notes;

    //private  Boolean rende_vouz;

    private Long  doctor_id;

    //private Long patient_id;

    private Long appointment_id;

    Integer startYear;

    Integer startMonth;

    Integer startDay;

}
