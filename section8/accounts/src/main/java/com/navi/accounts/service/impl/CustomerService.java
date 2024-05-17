package com.navi.accounts.service.impl;

import com.navi.accounts.dto.AccountDto;
import com.navi.accounts.dto.CardsDto;
import com.navi.accounts.dto.CustomerDetailsDto;
import com.navi.accounts.dto.LoanDto;
import com.navi.accounts.entity.Account;
import com.navi.accounts.entity.Customer;
import com.navi.accounts.exception.ResourceNotFoundException;
import com.navi.accounts.mapper.AccountMapper;
import com.navi.accounts.mapper.CustomerMapper;
import com.navi.accounts.repository.AccountRepository;
import com.navi.accounts.repository.CustomerRepository;
import com.navi.accounts.service.ICustomerService;
import com.navi.accounts.service.client.CardsClient;
import com.navi.accounts.service.client.LoansClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private CardsClient cardsClient;
    private LoansClient loansClient;


    public CustomerDetailsDto fetchCustomerAllDetails(String mobileNumber){
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customer Id", customer.getCustomerId().toString())
        );
        CardsDto cardsDto = null;
        if(cardsClient.fetch(mobileNumber) != null){
            cardsDto = cardsClient.fetch(mobileNumber).getBody();
        }
        LoanDto loanDto = null;
        if(loansClient.fetch(mobileNumber) != null){
            loanDto = loansClient.fetch(mobileNumber).getBody();
        }
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapAccountDto(account, new AccountDto()));
        customerDetailsDto.setCardsDto(cardsDto);
        customerDetailsDto.setLoanDto(loanDto);
        return  customerDetailsDto;
    }
}
