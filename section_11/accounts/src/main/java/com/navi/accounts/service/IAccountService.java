package com.navi.accounts.service;

import com.navi.accounts.dto.AccountDto;
import com.navi.accounts.dto.CustomerDto;
import com.navi.accounts.entity.Account;

public interface IAccountService {

    /*
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchCustomerDetails(String mobileNumber);

    boolean updateCustomer(CustomerDto customerDto);

    boolean updateAccount(AccountDto accountDto);

    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);
}
