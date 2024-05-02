package com.zeco.zecomedical.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lab_technicians")
@Data
public class LabTechnicians {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Users userID;

    @OneToOne
    @JoinColumn(name = "lab_department", referencedColumnName = "id")
    private Laboratories labDepartment;
}
