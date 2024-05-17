package com.navi.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Loan",
        description = "Schema to hold loan information"
)
public class LoanDto {

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "LoanNumber must be 10 digits")
    @Schema(
            example = "6767898912",
            description = "Mobile number of customer"
    )
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @Schema(
            example = "548732457654",
            description = "Loan Number of the customer"
    )
    private String loanNumber;

    @NotBlank
    @Schema(
            example = "Home Loan",
            description = "Type of loan"
    )
    private String loanType;


    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            example = "100000",
            description = "Total loan amount"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            example = "1000",
            description = "Total loan amount paid"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            example = "99000",
            description = "Total outstanding amount against a loan"
    )
    private int outstandingAmount;
}
