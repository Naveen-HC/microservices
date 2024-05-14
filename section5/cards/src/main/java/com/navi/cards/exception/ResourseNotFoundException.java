package com.navi.cards.exception;

public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(String resourcename, String fieldname, String value){
        super(String.format("%s not found with given input data %s: %s", resourcename,fieldname,value));
    }
}
