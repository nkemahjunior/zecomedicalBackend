package com.zeco.zecomedical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "lab_parasitology")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabParasitology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "consultation_id",referencedColumnName = "lab_results_parasitology")
    private Consultation labResultsParasitology;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id",nullable = false)
    private Doctors doctorID;

    @ManyToOne
    @JoinColumn(name = "lab_department",referencedColumnName = "id",nullable = false)
    private Laboratories labDepartment;

    @Column(name = "patient_name")
    private String patientName;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id",nullable = false)
    private RegisteredPatients patientID;


    // @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "lab_test_request",nullable = false)
    private String labTestRequest;

    @Column(name = "result")
    private Boolean result;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "creation_timestamp")
    private LocalDateTime creationTimestamp;


}
