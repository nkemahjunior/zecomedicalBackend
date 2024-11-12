package com.zeco.zecomedical.customExceptions;

import java.util.NoSuchElementException;

public class CustomOptionalIsEmptyException extends RuntimeException{

    public CustomOptionalIsEmptyException(String mssg){
        super(mssg);
    }
}
