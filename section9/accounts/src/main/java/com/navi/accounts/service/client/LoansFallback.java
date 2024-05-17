package com.navi.accounts.service.client;

import com.navi.accounts.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansClient {
    @Override
    public ResponseEntity<LoanDto> fetch(String mobileNumber) {
        return null;
    }
}
