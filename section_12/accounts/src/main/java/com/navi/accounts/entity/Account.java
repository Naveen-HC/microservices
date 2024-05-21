package com.navi.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Setter @Getter
public class Account extends BaseEntity {

    @Id
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branchAddress;
    private Boolean updateAccountsSw;
}
