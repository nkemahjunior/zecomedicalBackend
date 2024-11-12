package com.zeco.zecomedical.general.projections.consultations;

import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.PatientsTable;
import com.zeco.zecomedical.general.projections.lab.ConsultationTable;
import com.zeco.zecomedical.general.projections.patient.doctorsAvailable.DoctorsTable;

public interface ConsultationsProjection extends ConsultationTable {

    PatientsTable getPatientID();
    DoctorsTable getDoctorID();
}
