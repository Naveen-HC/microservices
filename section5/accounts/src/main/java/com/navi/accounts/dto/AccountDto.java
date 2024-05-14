package com.navi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Account",
        description = "Account schema holds  Account information of customer"
)
@Data @AllArgsConstructor @NoArgsConstructor
public class AccountDto {

    @Schema(
            example = "1090990909",
            description = "Account number of the customer"
    )
    private Long accountNumber;

    @Schema(
            example = "Saving/Current",
            description = "Account type of the customer"
    )
    @NotNull
    private String accountType;

    @Schema(
            example = "Main Street, New York",
            description = "NaviBank address"
    )
    @NotNull
    private String branchAddress;
}
