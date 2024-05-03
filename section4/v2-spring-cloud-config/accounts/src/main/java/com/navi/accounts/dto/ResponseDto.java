package com.navi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Response schema holds response information"
)
@Data @AllArgsConstructor
public class ResponseDto {

    @Schema(
            example = "200",
            description = "Code represents the status of request"
    )
    private String statusCode;

    @Schema(
            example = "Request processed successfully",
            description = "Message represents the status of request"
    )
    private String statusMsg;
}
