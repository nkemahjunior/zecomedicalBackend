package com.zeco.zecomedical.general.projections.patient.doctorsAvailable;

import java.time.LocalDateTime;

public interface DoctorsAvailableProjection {

    Long getId();
    DoctorsTable getDoctorID();
    LocalDateTime getTimeFrom();
    LocalDateTime getTimeTo();
}



