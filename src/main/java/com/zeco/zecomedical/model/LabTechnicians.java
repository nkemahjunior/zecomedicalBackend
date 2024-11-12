package com.zeco.zecomedical.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lab_technicians")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabTechnicians {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @NonNull
    private Users userID;

    @OneToOne
    @JoinColumn(name = "lab_department", referencedColumnName = "id")
    private Laboratories labDepartment;
}
