import com.company.gamelogic.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private final long TOTAL_CARDS_IN_DECK = 52;

    @Test
    public void whenDeckIsInstantiated_dequeOfLength52IsCreated(){
        Deck deck = new Deck();

        Assertions.assertEquals(TOTAL_CARDS_IN_DECK, deck.getAllCardsInDeck().size());
    }

    @Test
    public void whenDeckIsInstantiated_dequeContainsNoDuplicateCards(){
        Deck deck = new Deck();

        final long uniqueCards = deck.getAllCardsInDeck().stream().distinct().count();

        Assertions.assertEquals(TOTAL_CARDS_IN_DECK,uniqueCards);
    }

    //TODO : Might not be able to test for this
    @Test
    public void whenDeckIsInstantiated_dequeIsShuffled(){
        Deck unshuffledDeck = new Deck();

        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffleDeck();

    }
}
