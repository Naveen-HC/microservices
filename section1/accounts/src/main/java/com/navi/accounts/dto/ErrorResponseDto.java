package com.navi.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    private String apiPtah;
    private HttpStatus httpStatus;
    private String errorMsg;
    private LocalDateTime errorTime;
}
