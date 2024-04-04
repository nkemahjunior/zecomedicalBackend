package com.zeco.zecomedical.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;


import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * Apparently, Spring Security needs your Principal entity and all the entities that are referenced directly via relations to be classes implementing the Serializable interface:
 * */
public class Users implements Serializable { //implementing Serializable so Spring jdbc session will work


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "gender")
    @NonNull
    private String gender;

    @Column(name = "dob")//date of birth
    @NonNull
    private Calendar dob;

    @Column(name ="address")
    @NonNull
    private String address;

    @Column(name = "email")
    @NonNull
    private String email;


    @Column(name = "password")
    @NonNull
    private String password;

    @OneToOne
    @JoinColumn(name = "role" , referencedColumnName = "id")
    @NonNull
    private Roles role;

    @Column(name = "is_authenticated")
    @NonNull
    private Boolean isAuthenticated;

}
