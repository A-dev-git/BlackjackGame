import com.company.gamelogic.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGameTest {

    @Test
    public void whenDealerHasBlackjackAndSoDoesPlayer_PlayerWins(){
        List<Card> billiesCards = new ArrayList<>();
        billiesCards.add(new Card(CardRank.TEN,CardSuite.CLUBS));
        billiesCards.add(new Card(CardRank.FIVE,CardSuite.CLUBS));
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
            System.out.println(roundResult.getPlayer().getName() + " got " + roundResult.getPlayer().getHand().getCards());
        }

    }
}
