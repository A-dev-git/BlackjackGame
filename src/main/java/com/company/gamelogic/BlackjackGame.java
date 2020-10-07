package com.company.gamelogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlackjackGame {

    //TODO Should these be static or just final
    private static final int BUST_AMOUNT = 22;
    private static final int BLACKJACK_SCORE = 21;

    public static List<RoundResult> evaluateRound(List<Player> players, Dealer dealer){
        if(players == null || players.size() == 0) throw new RuntimeException("Player list must be a minimum length of 1");

        List<RoundResult> roundResults = new ArrayList<>();

        //Using iterator so that we can remove players from the round if they have busted while we are iterating. Using Iterator avoids ConcurrentModification exception
        Iterator<Player> itr = players.iterator();
        while (itr.hasNext()) {
            Player player = itr.next();
            if(isBust(player.getHand())){
                roundResults.add(new RoundResult(player,false,calculateHandScore(player.getHand())));
                itr.remove();
            }
        }

        //evaluate if any players have bust
//        players.forEach(player -> {
//            if(isBust(player.getHand())){
//                roundResults.add(new RoundResult(player,false,calculateHandScore(player.getHand())));
//            }
//        });

        //Check for black jacks
        if(isBlackJack(dealer.getHand())){
            players.forEach(player -> {
                if (isBlackJack(player.getHand())) {
                    roundResults.add(new RoundResult(dealer,false,calculateHandScore(dealer.getHand())));
                    roundResults.add(new RoundResult(player,true,calculateHandScore(player.getHand())));
                }else{
                    roundResults.add(new RoundResult(player,false,calculateHandScore(player.getHand())));
                }
            });

            return roundResults;
        }

        return roundResults;
    }

    public static boolean isBlackJack(Hand hand){
        return calculateHandScore(hand) == BLACKJACK_SCORE;
    }

    public static boolean isBust(Hand hand){
        return calculateHandScore(hand) >= BUST_AMOUNT;
    }

    //TODO Maybe minimize this with lambda or something
    public static int calculateHandScore(Hand hand){
        int handScore = 0;

        for(Card card : hand.getCards()){
            handScore+= card.getRank().value;
        }

        return handScore;
    }

}
