package com.zeco.zecomedical.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "registered_patients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredPatients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    private Long id;

//can the doctor edit this terms for the patient?
    @Column(name = "weight")
    //@NonNull
    private Float weight;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "bloodPressure")
    private String bloodPressure;

    @OneToOne
    @JoinColumn(name = "patients_id", referencedColumnName = "id")
    private Users patientID;

    @Column(name = "email")
    private String email;



}
