package com.zeco.zecomedical.auth.AuthDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
public class SignupResponseDto {

   // private  String isAuthenticated;
    private int status;
    private  String message;
}
