import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardDeckTest {

    @Test
    public void getDeckSizeTest() {
        CardDeck deck = new CardDeck(1);

        assertEquals("Incorrect deck size" , 0, deck.getDeckSize());
    }

    @Test
    public void addCardTest() {
        CardDeck deck = new CardDeck(1);
        Card card = new Card(1);
        deck.addToDeck(card);

        assertEquals("Card was not added to deck", 1, deck.getDeckSize());
    }
    
    @Test
    public void drawCardFromLeftTest() {
        CardDeck deck = new CardDeck(1);
        Card card = new Card(1);
        deck.addToDeck(card);
        Card drawnCard = deck.drawCardFromLeft();

        assertEquals("Card was not drawn from deck", 1, deck.getDeckSize());
        assertEquals("Incorrect card was drawn", card, drawnCard);
    }

    // @Test
    // public void addCardToRightTest() {
    //     CardDeck deck = new CardDeck(2);
    //     Card card = new Card(1);

    //     deck.addToDeck(card);
    //     deck.drawCardFromLeft();
    //     deck.addCardToRight(card);

    //     assertEquals("Card was not added to deck", 1, deck.getDeckSize());
    // }

    
}