package com.company.gamelogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlackjackGame {

    private static final int BUST_AMOUNT = 22;
    private static final int BLACKJACK_SCORE = 21;

    private static final int ACE_VALUE_ONE = 1;
    private static final int ACE_VALUE_TEN = 10;

    private static final int AUTO_WIN_CARD_AMOUNT = 5;

    public static List<RoundResult> evaluateRound(List<Player> players, Dealer dealer) {
        if(players == null || players.size() == 0) throw new InvalidPlayerListException("Player list must be a minimum length of 1");
        if(dealer == null) throw new InvalidDealerException("Dealer object cannot be null");

        List<RoundResult> roundResults = new ArrayList<>();

        //Check if any players bust, if so remove them from the list
        //Using iterator so that we can remove players from the round if they have busted while we are iterating. Using Iterator avoids ConcurrentModification exception
        for (Iterator<Player> iterator = players.iterator(); iterator.hasNext(); ) {
            Player player = iterator.next();

            if(isBust(player.getHand())){
                roundResults.add(new RoundResult(player,false,calculateHandScore(player.getHand())));
                iterator.remove();
            }

            if(players.size() == 0){
                return roundResults;
            }
        }

        //Check if players have 5 cards, if so they win
        for (Iterator<Player> iterator = players.iterator(); iterator.hasNext(); ) {
            Player player = iterator.next();
            if(handHasFiveCards(player.getHand())){
                roundResults.add(new RoundResult(player,true,calculateHandScore(player.getHand())));
                iterator.remove();
            }
        }

        //Check if dealer busts
        if(isBust(dealer.getHand())){
            players.forEach(player -> roundResults.add(new RoundResult(player,true,calculateHandScore(player.getHand()))));
            return roundResults;
        }

        //Check for black jacks
        if(isBlackJack(dealer.getHand())){
            players.forEach(player -> {
                if (isBlackJack(player.getHand())) {
                    roundResults.add(new RoundResult(player,true,calculateHandScore(player.getHand())));
                }else{
                    roundResults.add(new RoundResult(player,false,calculateHandScore(player.getHand())));
                }
            });
            return roundResults;
        }

        //Check if players hand score is higher than or equal to dealers
        players.forEach(player -> {
            if(calculateHandScore(player.getHand()) >= calculateHandScore(dealer.getHand())){
                roundResults.add(new RoundResult(player,true,calculateHandScore(player.getHand())));
            }else {
                roundResults.add(new RoundResult(player,false,calculateHandScore(player.getHand())));
            }
        });
        return roundResults;
    }

    public static boolean isBlackJack(Hand hand){
        return calculateHandScore(hand) == BLACKJACK_SCORE;
    }

    public static boolean handHasFiveCards(Hand hand){
        return hand.getCards().size() == AUTO_WIN_CARD_AMOUNT;
    }

    public static boolean isBust(Hand hand){
        return calculateHandScore(hand) >= BUST_AMOUNT;
    }

    public static int calculateHandScore(Hand hand){
        int handScore = 0;

        boolean aceInHand = false;
        int handScoreAceTen = 0;
        int handScoreAceOne = 0;

        for(Card card : hand.getCards()){
            if(card.getRank().equals(CardRank.ACE)){
                handScoreAceOne+= ACE_VALUE_ONE + handScore;
                handScoreAceTen+= ACE_VALUE_TEN + handScore;
                aceInHand = true;
            }else{
                handScore+= card.getRank().value;
            }
        }

        //return optimal outcome
        if(aceInHand){
            if(handScoreAceTen >= BUST_AMOUNT && handScoreAceOne <= BLACKJACK_SCORE){
                return handScoreAceOne;
            }else {
                return handScoreAceTen;
            }
        }

        return handScore;
    }

}
