/**
 * Card Deck class.
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class CardDeck {
    private int numberOfCards;
    private int deckId;
    private Card[] deckCards;

    /**
     * This is the constructor for a card deck.
     * 
     * @param deckId The id of the deck.
     * @version 1.0
     */
    CardDeck(int deckId) {
        this.numberOfCards = 0;
        this.deckId = deckId;
        this.deckCards = new Card[5];
    }

    /**
     * This is the getter for the number of cards in the deck.
     * 
     * @return The number of cards in the deck.
     * @version 1.0
     */
    public int getDeckId() {
        return this.deckId;
    }

    public int getDeckSize() {
        return this.numberOfCards;
    }

    /**
     * This is the method to add a card to the deck.
     * 
     * @param card The card to be added.
     * @version 1.0
     */
    public void addToDeck(Card card) {
        this.deckCards[numberOfCards++] = card;
    }

    /**
     * This is the method to draw a card from the left of the deck.
     * 
     * @return The card drawn.
     * @version 1.0
     */
    public Card drawCardFromLeft() {
        Card topCard = this.deckCards[0];
        for (int i = 0; i <= 3; i++) {                
            deckCards[i] = deckCards[i+1];
        }
        return topCard;
    }

    /**
     * This is the method to add a card to the right of the deck.
     * 
     * @param card The card to be added.
     * @version 1.0
     */
    public void addCardToRight(Card card) {
        if (this.deckCards[3] == null) {
            this.deckCards[3] = card;
        } else {
            this.deckCards[4] = card;
        }
    }
}
