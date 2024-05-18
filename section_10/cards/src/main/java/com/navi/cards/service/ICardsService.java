package com.navi.cards.service;

import com.navi.cards.dto.CardsDto;

public interface ICardsService {

    /**
     * param mobileNumber - Mobile number of customer
     */
    void  create(String mobileNumber);

    CardsDto fetch(String mobileNumber);

    boolean update(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
