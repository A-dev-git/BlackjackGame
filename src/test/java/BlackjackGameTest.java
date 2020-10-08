import com.company.gamelogic.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlackjackGameTest {

    public static Hand setupWinningHand(){
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(CardRank.TEN,CardSuite.CLUBS));
        playerCards.add(new Card(CardRank.TEN,CardSuite.HEARTS));
        playerCards.add(new Card(CardRank.ACE,CardSuite.DIAMONDS));
        return new Hand(playerCards);
    }

    public static Hand setupLosingHand(){
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(CardRank.TEN,CardSuite.CLUBS));
        playerCards.add(new Card(CardRank.TEN,CardSuite.HEARTS));
        playerCards.add(new Card(CardRank.TEN,CardSuite.DIAMONDS));
        return new Hand(playerCards);
    }

    @Test
    public void whenPlayerBusts_DealerWins(){
        Player billy = new Player("Billy",setupLosingHand());
        Dealer dealer = new Dealer("Dealer",setupWinningHand());

        List<Player> players = new ArrayList<>(){{ add(billy); }};

        List<RoundResult> results = BlackjackGame.evaluateRound(players,dealer);

        RoundResult dealerResult = null;
        RoundResult billyResult = null;

        for (RoundResult result : results){
            if (result.getPlayer() == dealer){
                dealerResult = result;
            }

            if(result.getPlayer() == billy){
                billyResult = result;
            }
        }

        Assertions.assertTrue(dealerResult.playerWon());
        Assertions.assertFalse(billyResult.playerWon());
    }

    @Test
    public void whenDealerBusts_AllPlayersThatHaveNotBustWin(){
        Player billy = new Player("Billy",setupWinningHand());
        Player lemmy = new Player("Lemmy",setupLosingHand());
        Dealer dealer = new Dealer("Dealer",setupLosingHand());

        List<Player> players = new ArrayList<>(){{ add(billy); add(lemmy); }};

        List<RoundResult> results = BlackjackGame.evaluateRound(players,dealer);

        RoundResult dealerResult = null;
        RoundResult billyResult = null;
        RoundResult lemmyResult = null;

        for (RoundResult result : results){
            if (result.getPlayer() == dealer){
                dealerResult = result;
            }

            if(result.getPlayer() == billy){
                billyResult = result;
            }

            if(result.getPlayer() == lemmy){
                lemmyResult = result;
            }
        }

        Assertions.assertFalse(dealerResult.playerWon());
        Assertions.assertTrue(billyResult.playerWon());
        Assertions.assertFalse(lemmyResult.playerWon());
    }

    @Test
    public void whenDealerAndPlayersGetBlackJack_PlayersWin(){
        Player billy = new Player("Billy",setupWinningHand());
        Player lemmy = new Player("Lemmy",setupWinningHand());
        Dealer dealer = new Dealer("Dealer",setupWinningHand());

        List<Player> players = new ArrayList<>(){{ add(billy); add(lemmy); }};

        List<RoundResult> results = BlackjackGame.evaluateRound(players,dealer);

        RoundResult dealerResult = null;
        RoundResult billyResult = null;
        RoundResult lemmyResult = null;

        for (RoundResult result : results){
            if (result.getPlayer() == dealer){
                dealerResult = result;
            }

            if(result.getPlayer() == billy){
                billyResult = result;
            }

            if(result.getPlayer() == lemmy){
                lemmyResult = result;
            }
        }

        Assertions.assertFalse(dealerResult.playerWon());
        Assertions.assertTrue(billyResult.playerWon());
        Assertions.assertTrue(lemmyResult.playerWon());
    }
}
