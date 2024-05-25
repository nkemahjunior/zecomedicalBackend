package com.zeco.zecomedical.testAttacks_home_work;


import com.zeco.zecomedical.dto.RequestResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController
public class Controller {

    private final AttacksRepository attacksRepository;

    @PostMapping("/test_homework_xss")
    public RequestResponse testXss(@RequestBody AttackDto xss ){

        Attacks attack = new Attacks();
        attack.setCode(xss.getName());

        attacksRepository.save(attack);

        return  RequestResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("saved the malicious code")
                .build();
    }


    @GetMapping("/test_homework_xss")
    public List<Attacks> getXssCode(){

      return   attacksRepository.findAll();
    }

    @GetMapping("/test_homework_xss2")
    public String getXssCode2(){

        return   "ggggggggggooooooooooooooooooo";
    }
}
