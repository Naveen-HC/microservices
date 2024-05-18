package com.navi.accounts.service.client;

import com.navi.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoansClient {

    @GetMapping(path = "/api/fetch", consumes = "application/json")
    ResponseEntity<LoanDto> fetch(@RequestParam String mobileNumber);
}
