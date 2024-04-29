package com.navi.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountDto {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
