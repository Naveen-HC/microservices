package com.navi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CardsDto {

    @Schema(
            description = "Mobile Number of Customer", example = "4354437687"
    )
    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must have 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Card Number of the customer", example = "100646930341"
    )
    @NotEmpty
    @Pattern(regexp = "^$|[0-9]{12}", message = "Card number must have 12 digits")
    private Long cardNumber;

    @Schema(
            description = "Type of the card", example = "Credit Card"
    )
    @NotNull
    private String cardType;

    @Schema(
            description = "Total amount limit available against a card", example = "100000"
    )
    @Positive(message = "Total card limit should be greater than zero")
    private Integer totalLimit;

    @Schema(
            description = "Total amount used by a Customer", example = "1000"
    )
    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private Integer amountUsed;

    @Schema(
            description = "Total available amount against a card", example = "90000"
    )
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private Integer availableAmount;
}
