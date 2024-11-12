package com.zeco.zecomedical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "consultation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id",nullable = false)
    private Doctors doctorID;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id",nullable = false)
    private RegisteredPatients patientID;

    @Column(name = "diagnosis_notes")
    private String diagnosisNotes;

    @Column(name = "timestamp",nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "come_for_checkup")
    private Boolean comeForCheckup;

    @Column(name = "checkup_date")
    private LocalDateTime checkupDate;


    //@OneToMany(mappedBy = "labResultsBloodBank")
    @Column(name = "lab_results_blood_bank")
    private UUID labResultsBloodBank;

    //@OneToMany(mappedBy = "labResultsImmunology")
    @Column(name = "lab_results_immunology")
    private UUID labResultsImmunology;

    //@OneToMany(mappedBy = "labResultsMicrobiology")
    @Column(name = "lab_results_microbiology")
    private UUID labResultsMicrobiology;

    //@OneToMany(mappedBy = "labResultsParasitology")
    @Column(name = "lab_results_parasitology")
    private UUID labResultsParasitology;

    @Column(name = "session_finished")
    private Boolean sessionFinished;

    @Column(name = "medecine_prescribed")
    private String medicinePrescribed;

    @Column(name = "status")
    private String status;

}
