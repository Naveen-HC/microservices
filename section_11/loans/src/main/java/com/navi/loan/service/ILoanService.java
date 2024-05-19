package com.navi.loan.service;

import com.navi.loan.dto.LoanDto;

public interface ILoanService {

    boolean create(String mobileNumber);

    LoanDto fecth(String mobileNumber);

    boolean update(LoanDto loanDto);

    boolean delete(String mobileNumber);
}
