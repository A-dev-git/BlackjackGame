package com.company.gamelogic;

import java.util.List;

public class Hand {

    //Using a list because we don't need to preserve the order of the cards
    private List<Card> cards;

    public Hand(List<Card> cards){
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
