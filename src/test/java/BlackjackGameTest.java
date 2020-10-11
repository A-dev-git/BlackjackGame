import com.company.gamelogic.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
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

        RoundResult billyResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == billy){
                billyResult = result;
            }
        }

        Assertions.assertFalse(billyResult.playerWon());
    }

    @Test
    public void whenDealerBusts_AllPlayersThatHaveNotBustWin(){
        Player billy = new Player("Billy",setupWinningHand());
        Player lemmy = new Player("Lemmy",setupLosingHand());
        Dealer dealer = new Dealer("Dealer",setupLosingHand());

        List<Player> players = new ArrayList<>(){{
            add(billy);
            add(lemmy);
        }};

        List<RoundResult> results = BlackjackGame.evaluateRound(players,dealer);

        RoundResult billyResult = null;
        RoundResult lemmyResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == billy){
                billyResult = result;
            }

            if(result.getPlayer() == lemmy){
                lemmyResult = result;
            }
        }

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

        RoundResult billyResult = null;
        RoundResult lemmyResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == billy){
                billyResult = result;
            }

            if(result.getPlayer() == lemmy){
                lemmyResult = result;
            }
        }

        Assertions.assertTrue(billyResult.playerWon());
        Assertions.assertTrue(lemmyResult.playerWon());
    }

    @Test
    public void whenDealerHasScoreCloserToBlackJack_PlayerLoses(){
        Player billy = new Player("Billy",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.TWO,CardSuite.SPADES));
                        add(new Card(CardRank.TWO,CardSuite.DIAMONDS));
                        add(new Card(CardRank.TWO,CardSuite.HEARTS));
                        add(new Card(CardRank.FOUR,CardSuite.DIAMONDS));
                    }
                }));

        Dealer dealer = new Dealer("Dealer", new Hand(new ArrayList<>(){
            {
                add(new Card(CardRank.JACK,CardSuite.SPADES));
                add(new Card(CardRank.NINE,CardSuite.HEARTS));
            }
        }));

        List<RoundResult> results = BlackjackGame.evaluateRound(new ArrayList<>(){{ add(billy); }},dealer);

        RoundResult billyResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == billy){
                billyResult = result;
            }

        }

        Assertions.assertFalse(billyResult.playerWon());
    }

    @Test
    public void whenPlayerHasScoreCloserToBlackJack_PlayerWins(){
        Player billy = new Player("Billy",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.JACK,CardSuite.SPADES));
                        add(new Card(CardRank.NINE,CardSuite.HEARTS));
                        add(new Card(CardRank.ACE,CardSuite.DIAMONDS));
                    }
                }));

        Dealer dealer = new Dealer("Dealer", new Hand(new ArrayList<>(){
            {
                add(new Card(CardRank.JACK,CardSuite.SPADES));
                add(new Card(CardRank.NINE,CardSuite.HEARTS));
            }
        }));

        List<RoundResult> results = BlackjackGame.evaluateRound(new ArrayList<>(){{ add(billy); }},dealer);

        RoundResult billyResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == billy){
                billyResult = result;
            }

        }

        Assertions.assertTrue(billyResult.playerWon());
    }

    @Test
    public void whenPlayerAndDealerHaveEqualHandScore_PlayerWins(){
        Player andrew = new Player("Andrew",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.KING,CardSuite.DIAMONDS));
                        add(new Card(CardRank.FOUR,CardSuite.SPADES));
                        add(new Card(CardRank.FOUR,CardSuite.CLUBS));
                    }
                }));

        Dealer dealer = new Dealer("Dealer", new Hand(new ArrayList<>(){
            {
                add(new Card(CardRank.KING,CardSuite.DIAMONDS));
                add(new Card(CardRank.FOUR,CardSuite.SPADES));
                add(new Card(CardRank.FOUR,CardSuite.CLUBS));
            }
        }));

        List<RoundResult> results = BlackjackGame.evaluateRound(new ArrayList<>(){{ add(andrew); }},dealer);

        RoundResult andrewResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == andrew){
                andrewResult = result;
            }

        }

        Assertions.assertTrue(andrewResult.playerWon());
    }

    @Test
    public void whenPlayerHasFiveCardsAndIsNotBust_PlayerWins(){
        Player billy = new Player("Billy",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.TWO,CardSuite.SPADES));
                        add(new Card(CardRank.TWO,CardSuite.DIAMONDS));
                        add(new Card(CardRank.TWO,CardSuite.HEARTS));
                        add(new Card(CardRank.FOUR,CardSuite.DIAMONDS));
                        add(new Card(CardRank.FIVE,CardSuite.CLUBS));
                    }
                }));
        Dealer dealer = new Dealer("Dealer",setupWinningHand());

        List<Player> players = new ArrayList<>(){{ add(billy); }};

        List<RoundResult> results = BlackjackGame.evaluateRound(players,dealer);

        RoundResult billyResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == billy){
                billyResult = result;
            }
        }

        Assertions.assertTrue(billyResult.playerWon());
    }

    //Checking that empty hands don't cause any errors
    @Test
    public void emptyHandTest(){
        Player billy = new Player("Billy", new Hand(new ArrayList<>()));
        Dealer dealer = new Dealer("Dealer",new Hand(new ArrayList<>()));

        List<Player> players = new ArrayList<>(){{ add(billy); }};

        List<RoundResult> results = BlackjackGame.evaluateRound(players,dealer);

        RoundResult billyResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == billy){
                billyResult = result;
            }
        }

        Assertions.assertTrue(billyResult.playerWon());
    }

    @Test
    public void multiplePlayersVersusDealerTestCase(){
        Player billy = new Player("Billy",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.TWO,CardSuite.SPADES));
                        add(new Card(CardRank.TWO,CardSuite.DIAMONDS));
                        add(new Card(CardRank.TWO,CardSuite.HEARTS));
                        add(new Card(CardRank.FOUR,CardSuite.DIAMONDS));
                        add(new Card(CardRank.FIVE,CardSuite.CLUBS));
                    }
                }));

        Player andrew = new Player("Andrew",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.KING,CardSuite.DIAMONDS));
                        add(new Card(CardRank.FOUR,CardSuite.SPADES));
                        add(new Card(CardRank.FOUR,CardSuite.CLUBS));
                    }
                }));

        Player lemmy = new Player("Lemmy",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.ACE,CardSuite.SPADES));
                        add(new Card(CardRank.SEVEN,CardSuite.HEARTS));
                        add(new Card(CardRank.ACE,CardSuite.DIAMONDS));
                    }
                }));

        Player carla = new Player("Carla",
                new Hand(new ArrayList<>(){
                    {
                        add(new Card(CardRank.QUEEN,CardSuite.CLUBS));
                        add(new Card(CardRank.SIX,CardSuite.SPADES));
                        add(new Card(CardRank.NINE,CardSuite.DIAMONDS));
                    }
                }));

        Dealer dealer = new Dealer("Dealer", new Hand(new ArrayList<>(){
            {
                add(new Card(CardRank.JACK,CardSuite.SPADES));
                add(new Card(CardRank.NINE,CardSuite.HEARTS));
            }
        }));

        List<RoundResult> results = BlackjackGame.evaluateRound(new ArrayList<>(){{
            add(andrew);
            add(billy);
            add(lemmy);
            add(carla);
        }},dealer);

        RoundResult andrewResult = null;
        RoundResult billyResult = null;
        RoundResult lemmyResult = null;
        RoundResult carlaResult = null;

        for (RoundResult result : results){
            if(result.getPlayer() == andrew){
                andrewResult = result;
            }

            if(result.getPlayer() == billy){
                billyResult = result;
            }

            if(result.getPlayer() == lemmy){
                lemmyResult = result;
            }

            if(result.getPlayer() == carla){
                carlaResult = result;
            }
        }

        Assertions.assertTrue(billyResult.playerWon());
        Assertions.assertFalse(andrewResult.playerWon());
        Assertions.assertFalse(lemmyResult.playerWon());
        Assertions.assertFalse(carlaResult.playerWon());
    }
}
