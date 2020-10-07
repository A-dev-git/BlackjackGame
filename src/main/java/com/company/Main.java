package com.company;

import com.company.gamelogic.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Card> billiesCards = new ArrayList<>();
        billiesCards.add(new Card(CardRank.TEN,CardSuite.CLUBS));
        billiesCards.add(new Card(CardRank.TEN,CardSuite.CLUBS));
        billiesCards.add(new Card(CardRank.ACE,CardSuite.CLUBS));
        billiesCards.add(new Card(CardRank.TEN,CardSuite.CLUBS));
        Hand billiesHand = new Hand(billiesCards);

        Player billy = new Player("Billy",billiesHand);

        List<Card> dealersCards = new ArrayList<>();
        dealersCards.add(new Card(CardRank.TEN,CardSuite.CLUBS));
        dealersCards.add(new Card(CardRank.TEN,CardSuite.DIAMONDS));
        dealersCards.add(new Card(CardRank.ACE,CardSuite.DIAMONDS));
        Hand dealersHand = new Hand(dealersCards);

        Dealer dealer = new Dealer("Dealer", dealersHand);

        List<Player> players = new ArrayList<>();
        players.add(billy);

        List<RoundResult> results = BlackjackGame.evaluateRound(players,dealer);

        for(RoundResult roundResult : results){
            System.out.println(roundResult.getPlayer().getName() + " had  ");
            roundResult.getPlayer().getHand().getCards().forEach(card -> System.out.println(card.getRank() + " of " + card.getSuite()));
            System.out.println("Total hand score : " + roundResult.getHandScore());

            System.out.println(roundResult.playerWon() ? roundResult.getPlayer().getName() + " Won." : roundResult.getPlayer().getName() + " Lost." );
            System.out.println("-----------------------------");
        }

    }
}