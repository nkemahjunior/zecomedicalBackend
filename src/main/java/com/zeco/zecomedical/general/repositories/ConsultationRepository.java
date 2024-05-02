package com.zeco.zecomedical.general.repositories;

import com.zeco.zecomedical.model.*;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

   // @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")

   /* @Query("SELECT c FROM Consultation c WHERE c.doctorID = ?1 and c.patientID = ?2 ORDER BY c.timestamp DESC LIMIT 1")
    Optional<Consultation> findByDoctorIDAndPatientID(Doctors doctor_id, RegisteredPatients patient_id);*/

    //always select the last one encase two was by mistakenly created
    @Query("SELECT c FROM Consultation c WHERE c.doctorID = ?1 and c.patientID = ?2 and c.sessionFinished = ?3 ORDER BY c.timestamp DESC LIMIT 1")
    Optional<Consultation> findByDoctorIDAndPatientIDAndSessionFinished(Doctors doctor,RegisteredPatients patients,Boolean sessionFinished);


    List<Consultation> findByPatientIDAndSessionFinished(RegisteredPatients patient,Boolean sessionFinished);

    Optional<Consultation> findByLabResultsBloodBank(UUID bloodBank);

    Optional<Consultation> findByLabResultsImmunology(UUID immunology);

    Optional<Consultation> findByLabResultsMicrobiology(UUID microbiology);

    Optional<Consultation> findByLabResultsParasitology(UUID parasitology);



}

