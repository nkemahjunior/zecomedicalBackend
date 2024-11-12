package com.zeco.zecomedical.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "waiting_lab_results")
@Data
@Builder
public class WaitingLabResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_id",nullable = false)
    private Long doctorID;

    @Column(name = "patient_id",nullable = false)
    private  Long patientID;

    @Column(name = "lab_name",nullable = false)
    private String labName;

    @Column(name = "completed",nullable = false)
    private Boolean completed;

    @Column(name = "date_time",nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "consultation_id",nullable = false)
    private UUID consultationID;
}
