package com.zeco.zecomedical.fileUploads.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadRequest {

    private String name;

    private MultipartFile file;
}
