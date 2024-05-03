package com.navi.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold response information"
)
public class ResponseDto {

    @Schema(
            description = "Represents status code of the request"
    )
    private String statusCode;
    @Schema(
            description = "Represents status message of the request"
    )
    private String statusMsg;
}
