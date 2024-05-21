package com.navi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer details schema",
        description = "CustomerDetails schema to holds customers account, card, loan details"
)
@Data
public class CustomerDetailsDto {

    @Schema(
            example = "Naveen",
            description = "Name of the customer"
    )
    @Size(min = 3, message = "Name must have least 3 characters")
    private String name;

    @Schema(
            example = "naveen@gmial.com",
            description = "Email address of the customer"
    )
    @Email(message = "Please enter valid email address")
    private String email;

    @Schema(
            example = "9867543267",
            description = "Mobile number of the customer"
    )
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must have 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountDto accountDto;

    @Schema(
            description = "Account details of the customer"
    )
    private CardsDto cardsDto;

    @Schema(
            description = "Account details of the customer"
    )
    private LoanDto loanDto;
}
