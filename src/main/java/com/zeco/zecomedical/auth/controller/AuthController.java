package com.zeco.zecomedical.auth.controller;


import com.zeco.zecomedical.auth.AuthDtos.SigninRequestDto;
import com.zeco.zecomedical.auth.AuthDtos.SignoutResponseDto;
import com.zeco.zecomedical.auth.AuthDtos.SignupResponseDto;
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
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/")
@Log4j2
@RequiredArgsConstructor
public class AuthController {

    private final Environment env;

    private final AuthenticationService authenticationService;
    public  final  VerifyEmailService verifyEmailService;


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

        MyDebug.printBlock();
        MyDebug.printBlock();
        System.out.println(env.getProperty("test"));
        MyDebug.printBlock();
        MyDebug.printBlock();

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


}
