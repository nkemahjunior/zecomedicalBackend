package com.zeco.zecomedical.customExceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends RuntimeException{

    int status;

    public MyException(int status, String msg){
        super(msg);
        this.status = status;

    }
}
