package com.zeco.zecomedical.fileUploads.controller;

import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.fileUploads.dto.FileUploadRequest;
import com.zeco.zecomedical.fileUploads.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload/")
@RequiredArgsConstructor
public class FileUploadController {

    private  final FileUploadService fileUploadService;

    @PostMapping(value = "/profilePicture", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<RequestResponse> uploadAndSaveEndpoint(@RequestPart("incomingFile") MultipartFile incomingFile){

        //when sending the details of your frontend form as FORM_DATA instead of JSON, u will use @RequestParam to get the value of each field of the form
        //use request part only for flies, well u could still use Request param for files

        return ResponseEntity.ok(fileUploadService.uploadAndSaveUrl(incomingFile));
    }
}
