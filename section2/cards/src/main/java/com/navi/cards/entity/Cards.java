package com.navi.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter @Getter
public class Cards extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String mobileNumber;
    private Long cardNumber;
    private String cardType;
    private Integer totalLimit;
    private Integer amountUsed;
    private Integer availableAmount;
}


