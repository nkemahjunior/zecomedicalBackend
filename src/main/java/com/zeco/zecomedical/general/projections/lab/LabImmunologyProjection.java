package com.zeco.zecomedical.general.projections.lab;

import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.PatientsTable;

public interface LabImmunologyProjection extends LabRequestProjections{

    PatientsTable getPatientID();
    ConsultationTable getLabResultsImmunology();
}
