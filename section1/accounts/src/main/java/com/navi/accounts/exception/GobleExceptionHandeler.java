package com.navi.accounts.exception;

import com.navi.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GobleExceptionHandeler {

    @ExceptionHandler(CustomerAlredayExistsException.class)
    public ResponseEntity<ErrorResponseDto> handelCustomerAlredayExistsException(CustomerAlredayExistsException exception,
                                                                                                WebRequest webRequest){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(), LocalDateTime.now()));
    }
}
