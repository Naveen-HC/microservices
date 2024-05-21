package com.navi.message.dto;

public record AccountsDto(Long accountNo, String name, String email, String mobileNo) {

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
