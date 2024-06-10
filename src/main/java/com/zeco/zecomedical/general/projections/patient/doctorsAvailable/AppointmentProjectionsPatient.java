package com.zeco.zecomedical.general.projections.patient.doctorsAvailable;

import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.PatientsTable;

import java.time.LocalDateTime;

public interface AppointmentProjectionsPatient{
    Long getId();
    String getStatus();
    String getReason();
    String getComplainNotes();
    DoctorsTable getDoctorID();
    LocalDateTime getDateTime();
}