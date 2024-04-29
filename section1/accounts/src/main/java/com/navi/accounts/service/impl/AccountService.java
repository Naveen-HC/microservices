package com.navi.accounts.service.impl;

import com.navi.accounts.constants.AccountConstants;
import com.navi.accounts.dto.CustomerDto;
import com.navi.accounts.entity.Account;
import com.navi.accounts.entity.Customer;
import com.navi.accounts.exception.CustomerAlredayExistsException;
import com.navi.accounts.mapper.CustomerMapper;
import com.navi.accounts.repository.AccountRepository;
import com.navi.accounts.repository.CustomerRepository;
import com.navi.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
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
        customer.setCreatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createAccount(savedCustomer));
    }

    /*
     * @param customer - Customer Object
     * @return new account details
     */
    private Account createAccount(Customer customer){
        Account account =  new Account();
        Long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(randomAccountNumber);
        account.setCustomerId(customer.getCustomerId());
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        return account;
    }
}
