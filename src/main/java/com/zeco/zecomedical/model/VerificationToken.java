package com.zeco.zecomedical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "verify_email")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne/*(targetEntity = User.class, fetch = FetchType.EAGER)*/
    @JoinColumn(name = "username",referencedColumnName = "username")
    private Users username;

    @Column(name = "token")
    private UUID token;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;







    // standard constructors, getters and setters
}