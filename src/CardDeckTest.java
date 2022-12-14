import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CardDeckTest {
  CardDeck deck = new CardDeck(1);
  Card card = new Card(1);

  // Tests whether the method returns the correct DeckId
  @Test
  public void getDeckIdTest() {
    int deckID = deck.getDeckId();

    assertTrue(deckID == 1);
  }

  // Tests if the deck size is correct
  @Test
  public void getDeckSizeTest() {
    assertTrue(deck.getDeckSize() == 0);
  }

  // Tests if the a card is added to the deck
  @Test
  public void addCardTest() {
    deck.addToDeck(card);

    assertTrue(deck.getDeckSize() == 1);
  }

  // Tests if a card is drawn from the left deck
  @Test
  public void drawCardFromLeftTest() {
    int cardNumber = card.getCardNumber();
    deck.addToDeck(card);
    Card drawnCard = deck.drawCardFromLeft();
    int drawnCardNumber = drawnCard.getCardNumber();

    assertTrue(deck.getDeckSize() == 1);
    assertEquals("Incorrect card was drawn", cardNumber, drawnCardNumber);
  }

  // Tests if a card is added to the right deck
  @Test
  public void addCardToRightTest() {
    int currentSize = deck.getDeckSize();
    assertTrue(currentSize == 0);

    deck.addToDeck(card);
    assertTrue(deck.getDeckSize() == 1);

    deck.drawCardFromLeft();
    assertTrue(currentSize == 0);

    deck.addCardToRight(card);
    assertTrue(deck.getDeckSize() == 1);
    }
}