package com.zeco.zecomedical.doctorsPostAppointment.dtos;

import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.DoctorsAvailableForAppointment;
import com.zeco.zecomedical.model.RegisteredPatients;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MyAppointmentsResponse {

    private  Long id;

   // private String day;

    //private LocalDateTime time_from;

  //  private LocalDateTime time_to;

    private String status;

    private String reason;

    private  String complain_notes;

    private  Boolean rende_vouz;

    private Doctors doctorID;

    private RegisteredPatients patient_id;

    private DoctorsAvailableForAppointment appointment_id;

    private LocalDateTime dateTime;



}
