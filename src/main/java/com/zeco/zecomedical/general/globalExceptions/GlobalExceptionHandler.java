package com.zeco.zecomedical.general.globalExceptions;


import com.zeco.zecomedical.auth.customExceptionsDtos.CustomErrorResponse;
import com.zeco.zecomedical.customExceptions.CustomOptionalIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(BadCredentialsException exc){

      CustomErrorResponse error = CustomErrorResponse.builder()
              .status(HttpStatus.UNAUTHORIZED.value())
              .message("Bad credentials, check username and or password")
              .build();

        return  new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public  ResponseEntity<CustomErrorResponse> optionalExceptions(CustomOptionalIsEmptyException e){

        CustomErrorResponse error = CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Bad credentials, check username and or password")
                .build();

        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
