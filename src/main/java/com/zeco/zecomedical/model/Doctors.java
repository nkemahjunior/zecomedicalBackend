package com.zeco.zecomedical.model;


import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
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

}
