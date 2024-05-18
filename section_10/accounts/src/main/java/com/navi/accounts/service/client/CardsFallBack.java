package com.navi.accounts.service.client;

import com.navi.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsClient{
    @Override
    public ResponseEntity<CardsDto> fetch(String mobileNumber) {
        return null;
    }
}
