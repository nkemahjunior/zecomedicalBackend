package com.zeco.zecomedical.auth.AuthDtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninRequestDto {

    private String username;
    private   String password;
}
