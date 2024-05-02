package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.AppointmentRequests;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.RegisteredPatients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRequestsRepository extends JpaRepository<AppointmentRequests,Long> {

    List<AppointmentRequests> findByDoctorID(Doctors doctor);

    List<AppointmentRequests> findByDoctorIDAndDateTimeGreaterThan(Doctors doctor, LocalDateTime dateTime);

    List<AppointmentRequests> findByDoctorIDAndStatusAndDateTimeGreaterThan(Doctors doctor, String status, LocalDateTime dateTime);

    List<AppointmentRequests> findByDoctorIDAndPatientIDAndStatusAndDateTimeGreaterThan(Doctors doctor,RegisteredPatients patient, String status, LocalDateTime dateTime);

    List<AppointmentRequests> findByPatientID(RegisteredPatients patient);

    List<AppointmentRequests> findByPatientIDAndStatusAndDateTimeGreaterThan(RegisteredPatients patient,String status,LocalDateTime dateTime);
}
