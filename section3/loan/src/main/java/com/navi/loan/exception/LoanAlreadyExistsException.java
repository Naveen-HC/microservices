package com.navi.loan.exception;

public class LoanAlreadyExistsException extends RuntimeException{

    public LoanAlreadyExistsException(String messsage){
        super(messsage);
    }
}
