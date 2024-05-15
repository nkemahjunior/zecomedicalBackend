package com.zeco.zecomedical.notification.service.controller;


import com.zeco.zecomedical.auth.verifyEmail.VerifyEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailVerifiedController {

    private  final VerifyEmailService verifyEmailService;

    @GetMapping("/confirm-email") ///confirm-email?token=....
    public String confirmEmail(@RequestParam(name = "token") UUID token, Model model){

        verifyEmailService.validateEmailToken(( token));

        model.addAttribute("setUpAccountUrl","http://localhost:3000/");
        return  "confirmedEmail.html";
    }


}
