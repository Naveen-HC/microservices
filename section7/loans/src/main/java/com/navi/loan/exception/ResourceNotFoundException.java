package com.navi.loan.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource, String filed, String value){
        super(String.format("%s not found due to invalid %s: %s", resource, filed, value));
    }
}
