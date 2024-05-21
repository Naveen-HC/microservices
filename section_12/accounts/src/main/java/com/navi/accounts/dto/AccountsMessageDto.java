package com.navi.accounts.dto;

public record AccountsMessageDto(Long accountNo, String name, String email, String mobileNo) {

    @Override
    public String toString() {
        return "AccountsDto{" +
                "accountNo=" + accountNo +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
