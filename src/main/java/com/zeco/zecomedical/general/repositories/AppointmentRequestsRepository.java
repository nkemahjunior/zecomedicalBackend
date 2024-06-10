package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.general.projections.doctors.appointmentRequest.MyAppointmentRequestProjections;
import com.zeco.zecomedical.general.projections.patient.doctorsAvailable.AppointmentProjectionsPatient;
import com.zeco.zecomedical.model.AppointmentRequests;
import com.zeco.zecomedical.model.Doctors;
import com.zeco.zecomedical.model.RegisteredPatients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRequestsRepository extends JpaRepository<AppointmentRequests,Long> {

    List<AppointmentRequests> findByDoctorID(Doctors doctor);
//
    Page<MyAppointmentRequestProjections> findByDoctorIDAndDateTimeGreaterThanAndStatusEquals(Doctors doctor, LocalDateTime dateTime,String status, Pageable pageable);
//
    Page<MyAppointmentRequestProjections> findByDoctorIDAndStatusAndDateTimeGreaterThan(Doctors doctor, String status, LocalDateTime dateTime,Pageable pageable);

    List<AppointmentRequests> findByDoctorIDAndPatientIDAndStatusAndDateTimeGreaterThan(Doctors doctor,RegisteredPatients patient, String status, LocalDateTime dateTime);

    List<AppointmentRequests> findByPatientID(RegisteredPatients patient);

    List<AppointmentProjectionsPatient> findByPatientIDAndStatusAndDateTimeGreaterThanOrderByDateTime(RegisteredPatients patient, String status, LocalDateTime dateTime);

    Boolean existsByPatientIDAndDateTime(RegisteredPatients patient , LocalDateTime dateTime);
}



