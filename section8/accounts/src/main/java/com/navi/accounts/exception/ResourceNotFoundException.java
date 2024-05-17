package com.navi.accounts.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourcename, String fieldname, String value){
        super(String.format("%s not found with given input data %s: %s", resourcename,fieldname,value));
    }
}
