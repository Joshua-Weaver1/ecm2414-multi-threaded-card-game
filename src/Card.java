/**
 * This is the class for a card.
 *
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */

public class Card {
    private final int cardNumber;

    public Card(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "" + cardNumber;
    }
}