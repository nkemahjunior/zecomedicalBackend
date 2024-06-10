package com.zeco.zecomedical.auth.AuthDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignoutResponseDto {

    private Integer status;
    private String message;
}
