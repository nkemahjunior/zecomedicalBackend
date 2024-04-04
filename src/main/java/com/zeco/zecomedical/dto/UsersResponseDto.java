package com.zeco.zecomedical.dto;

import com.zeco.zecomedical.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDto {

        private String errorMessage = null;
        //private Boolean okk =false;

        private String name;

        private String username;

        private String gender;

        private Calendar dob;

        private String address;

        private String email;

        //private String password;

        private Roles role;

        private Boolean isAuthenticated = false;


    }
