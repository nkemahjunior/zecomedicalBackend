package com.zeco.zecomedical.general.projections.doctors.appointmentRequest;

import java.time.LocalDateTime;

public interface MyAppointmentRequestProjections{
    Long getId();
    String getStatus();
    String getReason();
    String getComplainNotes();
    PatientsTable getPatientID();
    LocalDateTime getDateTime();
}




