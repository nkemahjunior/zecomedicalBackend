package com.zeco.zecomedical.general.projections.lab;

import com.zeco.zecomedical.general.projections.patient.doctorsAvailable.DoctorsTable;
import com.zeco.zecomedical.model.Laboratories;

import java.time.LocalDateTime;

public interface LabRequestProjections {
    Long getId();
    DoctorsTable getDoctorID();
    String getPatientName();
    Laboratories getLabDepartment();
    String getLabTestRequest();
    Boolean getResult();
    Boolean getCompleted();
    LocalDateTime getCreationTimestamp();
    String getNotes();

}
