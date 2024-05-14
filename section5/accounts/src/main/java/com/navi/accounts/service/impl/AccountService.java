package com.navi.accounts.service.impl;

import com.navi.accounts.constants.AccountConstants;
import com.navi.accounts.dto.AccountDto;
import com.navi.accounts.dto.CustomerDto;
import com.navi.accounts.entity.Account;
import com.navi.accounts.entity.Customer;
import com.navi.accounts.exception.CustomerAlredayExistsException;
import com.navi.accounts.exception.ResourceNotFoundException;
import com.navi.accounts.mapper.AccountMapper;
import com.navi.accounts.mapper.CustomerMapper;
import com.navi.accounts.repository.AccountRepository;
import com.navi.accounts.repository.CustomerRepository;
import com.navi.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;


    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(customerOptional.isPresent()){
            throw new CustomerAlredayExistsException("Already customer exists with mobile number " +
                    customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        Account savedAccount = accountRepository.save(createAccount(savedCustomer));
        if(savedAccount.getAccountNumber() == null){
            customerRepository.delete(customer);
        }
    }

    /**
     * @param customer - Customer Object
     * @return new account details
     */
    private Account createAccount(Customer customer){
        Account account =  new Account();
        Long randomAccountNumber = 1000000000L + new Random().nextLong(900000000L);
        account.setAccountNumber(randomAccountNumber);
        account.setCustomerId(customer.getCustomerId());
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        return account;
    }

    public CustomerDto fetchCustomerDetails(String mobileNumber){
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customer Id", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapAccountDto(account, new AccountDto()));
        return customerDto;
    }

    public boolean updateCustomer(CustomerDto customerDto){
        boolean isUpdated = false;
        Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", customerDto.getMobileNumber())
        );
        if(customer != null) {
            customer = CustomerMapper.mapCustomer(customerDto, customer);
            Customer savedCustomer = customerRepository.save(customer);
            Account account = accountRepository.findByAccountNumber(
                    customerDto.getAccountDto().getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "account number",
                            customerDto.getAccountDto().getAccountNumber().toString())
            );
            account = AccountMapper.mapAccount(customerDto.getAccountDto(), account);
            accountRepository.save(account);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean updateAccount(AccountDto accountDto){
        Account account = accountRepository.findByAccountNumber(accountDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "account number", accountDto.getAccountNumber().toString())
        );
        account = AccountMapper.mapAccount(accountDto, account);
        accountRepository.save(account);
        return true;
    }

    public boolean deleteAccount(String mobileNumber){
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
        if(customer != null) {
            Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "customer Id", customer.getCustomerId().toString())
            );
            customerRepository.delete(customer);
            accountRepository.delete(account);
            return true;
        } else{
            return false;
        }
    }
}
