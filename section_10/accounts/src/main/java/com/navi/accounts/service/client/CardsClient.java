package com.navi.accounts.service.client;

import com.navi.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", fallback = CardsFallBack.class)
public interface CardsClient {

    @GetMapping(path = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetch(@RequestParam String mobileNumber);
}
