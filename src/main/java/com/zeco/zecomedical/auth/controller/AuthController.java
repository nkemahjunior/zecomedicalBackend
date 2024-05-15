package com.zeco.zecomedical.auth.controller;


import com.zeco.zecomedical.auth.AuthDtos.LogoutRequest;
import com.zeco.zecomedical.auth.AuthDtos.SigninRequestDto;
import com.zeco.zecomedical.auth.AuthDtos.SignoutResponseDto;
import com.zeco.zecomedical.auth.AuthDtos.SignupResponseDto;
import com.zeco.zecomedical.auth.activateAccounts.ActivateAccountsService;
import com.zeco.zecomedical.auth.activateAccounts.activationDtos.ActivateDoctorAccountRequest;
import com.zeco.zecomedical.auth.activateAccounts.activationDtos.ActivateLabTechAccountRequest;
import com.zeco.zecomedical.auth.activateAccounts.activationDtos.ActivatePatientAccountRequest;
import com.zeco.zecomedical.auth.service.AuthenticationService;
import com.zeco.zecomedical.auth.verifyEmail.VerifyEmailService;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.dto.UsersRequestDto;
import com.zeco.zecomedical.dto.UsersResponseDto;
import com.zeco.zecomedical.general.utils.MyDebug;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/auth/")
@Log4j2
@RequiredArgsConstructor

public class AuthController {

    private final AuthenticationService authenticationService;
    public  final  VerifyEmailService verifyEmailService;
    private  final ActivateAccountsService activateAccountsService;

    //this functionality has been moved to the notification service
    /*@GetMapping("/confirm-email") ///confirm-email?token=....
    public ResponseEntity<RequestResponse> confirmEmail(@RequestParam(name = "token") UUID token){

        return ResponseEntity.ok(verifyEmailService.validateEmailToken(( token)));
    }*/

    @GetMapping("/resendVerificationEmail") ///resendVerificationEmail?email=....
    public ResponseEntity<RequestResponse> resendVerificationEmailEndpoint(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(verifyEmailService.resendVerificationEmail(email));
    }



    @GetMapping("/session")
    public UsersResponseDto checkUserSession(HttpServletRequest request){
            return  authenticationService.checkSession(request);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("whatttt is happening");
    }


    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody UsersRequestDto signupData)  {
        return ResponseEntity.ok(authenticationService.signup(signupData));
    }

    @PostMapping("/signin")
    public ResponseEntity<UsersResponseDto> signin(@RequestBody SigninRequestDto signinData, HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(authenticationService.signin(signinData, request, response));
    }

    @PostMapping("/signout")
    public ResponseEntity<SignoutResponseDto> signout(Authentication authentication, HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(authenticationService.signout(authentication, request, response));
    }

    @PostMapping("/activatePatientAccount")
    public ResponseEntity<RequestResponse> activatePatientAccountEndpoinr(@RequestBody ActivatePatientAccountRequest request){
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
