package com.navi.accounts.exception;

public class CustomerAlredayExistsException extends RuntimeException{

    public CustomerAlredayExistsException(String message){
        super(message);
    }
}
