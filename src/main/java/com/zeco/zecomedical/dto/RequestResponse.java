package com.zeco.zecomedical.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestResponse {

    private int status;
    private String message;
}
