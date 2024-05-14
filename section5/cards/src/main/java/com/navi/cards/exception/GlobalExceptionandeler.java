package com.navi.cards.exception;

import com.navi.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionandeler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errorsMsg = new HashMap<>();
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        objectErrors.forEach(error -> errorsMsg.put(((FieldError)error).getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMsg);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handelGlobalException(Exception exception,
                                                                             WebRequest webRequest){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(
                        webRequest.getDescription(false),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        exception.getMessage(),
                        LocalDateTime.now()));
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handelCardAlresdyExistsException(CardAlreadyExistsException exception,
                                                                             WebRequest webRequest){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(
                        webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(),
                        LocalDateTime.now()));
    }

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handelResourseNotFoundException(ResourseNotFoundException exception,
                                                                            WebRequest webRequest){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(
                        webRequest.getDescription(false),
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        LocalDateTime.now()));
    }
}
