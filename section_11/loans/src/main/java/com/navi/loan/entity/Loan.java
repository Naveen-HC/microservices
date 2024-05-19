package com.navi.loan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@Table(name = "loans")
public class Loan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private Integer totalLoan;
    private Integer amountPaid;
    private Integer outstandingAmount;
}
