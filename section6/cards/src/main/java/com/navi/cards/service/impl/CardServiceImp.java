package com.navi.cards.service.impl;

import com.navi.cards.constants.CardsConstants;
import com.navi.cards.dto.CardsDto;
import com.navi.cards.entity.Cards;
import com.navi.cards.exception.CardAlreadyExistsException;
import com.navi.cards.exception.ResourseNotFoundException;
import com.navi.cards.mapper.CardsMapper;
import com.navi.cards.repository.CardsRepository;
import com.navi.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImp implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile number of customer
     */
    @Override
    public void create(String mobileNumber) {
        Optional<Cards> optionalCard =cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()){
            throw new CardAlreadyExistsException("Card already exists for mobile number "
            + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile number of customer
     * @return new card details of customer
     */
    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        Long randomCardNumber = 1000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(randomCardNumber);
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        return newCard;
    }

    /**
     * @param mobileNumber - Mobile number of customer
     * @return card details
     */
   @Override
   public CardsDto fetch(String mobileNumber){
      Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
              () -> new ResourseNotFoundException("card", "mobile number", mobileNumber)
      );
      return CardsMapper.mapCardsDto(card, new CardsDto());
   }

   /**
    * @param cardsDto - CardsDto Object
    * @return boolean indicating if the update of card details is successful or not
    */
   public boolean update(CardsDto cardsDto){
        Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourseNotFoundException("card", "Card number", cardsDto.getCardNumber().toString())
        );
        if(cardsRepository.save(CardsMapper.mapCards(cardsDto, card)) != null){
            return true;
        }
        return false;
   }
    /**
     * @param mobileNumber - Mobile number of customer
     * @return boolean indicating if the delete of card details is successful or not
     */
   public boolean deleteCard(String mobileNumber){
       Cards card =cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourseNotFoundException("card", "mobile number", mobileNumber)
       );
       cardsRepository.delete(card);
       return true;
   }
}
