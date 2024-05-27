package com.zeco.zecomedical.general.projections.lab;

import com.zeco.zecomedical.general.projections.patient.doctorsAvailable.DoctorsTable;

import java.time.LocalDateTime;

public interface LabRequestProjections {
    Long getId();
    DoctorsTable getDoctorID();
    String getPatientName();
    String getLabTestRequest();
    Boolean getResult();
    Boolean getCompleted();
    LocalDateTime getCreationTimestamp();
    String getNotes();
}
