package com.zeco.zecomedical.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;


import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "doctors_available_for_appointments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoctorsAvailableForAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //cascade.delete and update are handle by the database
    //@ManyToOne(fetch = FetchType.LAZY) : is producing an error ,so we will write our own query for data we want to select and from which table at the findAll() method in the repository for this table/class
    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id")
    @NonNull
    private Doctors doctorID;

    /*@JoinColumn(name = "uuid" ,referencedColumnName = "id")
    @Column(name = "uuid")
    @NonNull
    private UUID uuid;*/

   /* @Column(name = "day")
    @NonNull
    private String day;*/

    @Column(name = "time_from")
    @NonNull
    private LocalDateTime timeFrom;

    @Column(name = "time_to")
    @NonNull
    private LocalDateTime timeTo;//the method i want to use is found in Builder which is a static class in Calender

    /*@Column(name = "name")
    //@NonNull
    private String name;*/
}
