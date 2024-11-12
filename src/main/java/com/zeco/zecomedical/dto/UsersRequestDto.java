package com.zeco.zecomedical.dto;

import com.zeco.zecomedical.model.Roles;
import lombok.Data;


@Data
public class UsersRequestDto {


    private String name;
    private String username;
    private String gender;
    private Integer year;
    private Integer month;
    private Integer day;
    private String address;
    private String email;
    private String password;
    //private Roles role;
    private Boolean isAuthenticated;

}
