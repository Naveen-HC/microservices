package com.navi.accounts.service.client;

import com.navi.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansClient {

    @GetMapping(path = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoanDto> fetch(@RequestParam String mobileNumber);
}
