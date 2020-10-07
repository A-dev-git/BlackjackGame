package com.company.gamelogic;

import java.util.*;

public class Deck {

    // Using a deque instead of a list because we want to preserve the order of the cards.
    // Deque also allows use to add or remove from either end of the collection.
    private Deque<Card> cards;

    public Deck(){
        List<Card> unshuffledcards = new ArrayList<>();

        //TODO : Possibly minimize this code with a lambda
        for(CardSuite suite : CardSuite.values()){
            for(CardRank rank : CardRank.values()){
                unshuffledcards.add(new Card(rank,suite));
            }
        }

        cards = new ArrayDeque<>(unshuffledcards);
    }

    public Deque<Card> getAllCardsInDeck(){
        return cards;
    }

    public void shuffleDeck(){
        List<Card> unshuffledCards = new ArrayList<>(cards);
        Collections.shuffle(unshuffledCards);
        cards = new ArrayDeque<>(unshuffledCards);
    }

    //TODO : double check that the logic for adding a card to the bottom of the pile is correct
    public void addCard(Card card){
        cards.addLast(card);
    }

    //TODO : double check that the logic for removing a card from the top of the pile is correct
    public Card drawCard(){
        Card card = cards.getFirst();
        cards.removeFirst();

        return card;
    }
}
