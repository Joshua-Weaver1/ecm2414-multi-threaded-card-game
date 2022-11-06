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

    CardDeck(int deckId) {
        this.numberOfCards = 0;
        this.deckId = deckId;
        this.deckCards = new Card[5];
    }

    public int getDeckId() {
        return this.deckId;
    }

    public void addToDeck(Card card) {
        this.deckCards[numberOfCards++] = card;
    }

    public Card drawCardFromLeft(Card card) {
        Card topCard = this.deckCards[0];
        for (int i = 0; i <= 4; i++) {                
            deckCards[i] = deckCards[i+1];
        }
        return topCard;
    }

    public void addCardToRight(Card card) {
        if (this.deckCards[3] == null) {
            this.deckCards[3] = card;
        } else {
            this.deckCards[4] = card;
        }
    }
}
