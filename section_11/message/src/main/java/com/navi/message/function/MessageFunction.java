package com.navi.message.function;

import com.navi.message.dto.AccountsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<AccountsDto, AccountsDto> email(){
        return accountsDto -> {
            LOGGER.info("Sending email with details: {}", accountsDto);
            return accountsDto;
        };
    }

    @Bean
    public Function<AccountsDto, Long> sms(){
        return accountsDto -> {
            LOGGER.info("sending sms with details: " + accountsDto);
            return  accountsDto.accountNo();
        };
    }
}
