package com.navi.accounts.function;

import com.navi.accounts.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsFunction.class);

    @Bean
    public Consumer<Long> updateAccount(IAccountService iAccountService){
        return  accountsNumber -> {
            LOGGER.info("Account number sent by Message App is {}", accountsNumber);
            iAccountService.updateCommunicationStatus(accountsNumber);
        };
    }
}
