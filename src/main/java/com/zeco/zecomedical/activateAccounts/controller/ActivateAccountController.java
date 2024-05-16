package com.zeco.zecomedical.activateAccounts.controller;


import com.zeco.zecomedical.activateAccounts.ActivateAccountsService;
import com.zeco.zecomedical.activateAccounts.activationDtos.ActivateDoctorAccountRequest;
import com.zeco.zecomedical.activateAccounts.activationDtos.ActivateLabTechAccountRequest;
import com.zeco.zecomedical.activateAccounts.activationDtos.ActivatePatientAccountRequest;
import com.zeco.zecomedical.dto.RequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/")
@RequiredArgsConstructor
public class ActivateAccountController {

    private  final ActivateAccountsService activateAccountsService;


    @PostMapping("/activatePatientAccount")
    public ResponseEntity<RequestResponse> activatePatientAccountEndpoint(@RequestBody ActivatePatientAccountRequest request){
        return  ResponseEntity.ok(activateAccountsService.activatePatientAccount(request));
    }

    @PostMapping("/activateDoctorAccount")
    public ResponseEntity<RequestResponse> activateDoctorAccountEndpoint(@RequestBody ActivateDoctorAccountRequest request){
        return  ResponseEntity.ok(activateAccountsService.activateDoctorAccount(request));
    }

    @PostMapping("/activateLabTechnicianAccount")
    public ResponseEntity<RequestResponse> activateLabTechnicianAccountEndpoint(@RequestBody ActivateLabTechAccountRequest request){
        return ResponseEntity.ok(activateAccountsService.activateLabTechnicianAccount(request));
    }
}
