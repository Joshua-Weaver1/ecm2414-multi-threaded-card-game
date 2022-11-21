/**
 * This is the class for a card.
 *
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class Card {
    private final int cardNumber;

    /**
     * This is the constructor for a card.
     * 
     * @param cardNumber The number of the card.
     * @version 1.0
     */
    public Card(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * This is the getter for the card number.
     * 
     * @return The card number.
     * @version 1.0
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * This is the toString method for a card.
     * 
     * @return The card number.
     * @version 1.0
     */
    @Override
    public String toString() {
        return "" + cardNumber;
    }
}