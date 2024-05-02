package com.zeco.zecomedical.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctors")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctor_id;


    //cascade.delete and update are handle by the database, if you delete a user ,the referencing doctor account gets deleted and NOT vice versa
    @OneToOne
    @JoinColumn(name = "uuid", referencedColumnName = "id")
    private Users uuid;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "email")
    private String email;
}
