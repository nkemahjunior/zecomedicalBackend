package com.zeco.zecomedical.fileUploads.dto;

import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.model.Roles;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadResponse extends RequestResponse {


    Roles role;
}
