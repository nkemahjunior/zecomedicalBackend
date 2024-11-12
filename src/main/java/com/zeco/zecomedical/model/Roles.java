package com.zeco.zecomedical.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Entity
@Table(name = "user_roles")
@Data
/**
 * Apparently, Spring Security needs your Principal entity and all the entities that are referenced directly via relations to be classes implementing the Serializable interface:
 * */
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "roles")
    private String roles;


}
