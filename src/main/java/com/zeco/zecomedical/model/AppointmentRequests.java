package com.zeco.zecomedical.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_requests")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //@NonNull
    private Long id;

   /* @Column(name = "day")
    //@NonNull
    private String day;

    @Column(name = "time_from")
    //@NonNull
    private LocalDateTime time_from;

    @Column(name = "time_to")
    //@NonNull
    private LocalDateTime time_to;*/

    @Column(name = "status")
    @NonNull
    private String status;

    @Column(name = "reason")
    @NonNull
    private String reason;
    
    @Column(name = "complain_notes")
    @NonNull
    private  String complain_notes;
    
    @Column(name  = "rende_vouz")
    @NonNull
    private  Boolean rende_vouz;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id")
    @NonNull
    private Doctors doctorID;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    @NonNull
    private RegisteredPatients patientID;

    @ManyToOne
    @JoinColumn(name = "appointment_id",referencedColumnName = "id")
   // @NonNull
    private DoctorsAvailableForAppointment appointment_id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;


}
