package com.zeco.zecomedical.auth.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/test1")
    public String test1(){
        return  "security working";
    }
}
