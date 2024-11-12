package com.zeco.zecomedical.treatmentProcess.dto;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
@Data
public class SendToLabResponse {

    Integer status;
    String message;
    UUID consultationID;
}
