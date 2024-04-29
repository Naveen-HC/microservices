package com.navi.accounts.service;

import com.navi.accounts.dto.AccountDto;
import com.navi.accounts.dto.CustomerDto;

public interface IAccountService {

    /*
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);
}
