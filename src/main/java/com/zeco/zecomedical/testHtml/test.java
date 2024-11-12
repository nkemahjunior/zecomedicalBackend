package com.zeco.zecomedical.testHtml;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class test {

    @GetMapping("/testEmail")
    public String testHtml(){

        return "emailVerification.html";
    }
}
