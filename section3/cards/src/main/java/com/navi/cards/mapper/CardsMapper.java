package com.navi.cards.mapper;

import com.navi.cards.dto.CardsDto;
import com.navi.cards.entity.Cards;

public class CardsMapper {

    public static Cards mapCards(CardsDto cardsDto, Cards cards){
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
       return  cards;
    }

    public static CardsDto mapCardsDto(Cards cards, CardsDto cardsDto){
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        return  cardsDto;
    }
}
