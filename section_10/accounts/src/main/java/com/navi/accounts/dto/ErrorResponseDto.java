package com.navi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "ErrorResponse schema holds error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "apiPtah represents the request path"
    )
    private String apiPtah;

    @Schema(
            description = "httpStatus represents the status of request"
    )
    private HttpStatus httpStatus;

    @Schema(
            description = "errorMsg represents the describes the cause of error"
    )
    private String errorMsg;

    @Schema(
            description = "errorTime represents the time at which error occurred"
    )
    private LocalDateTime errorTime;
}
