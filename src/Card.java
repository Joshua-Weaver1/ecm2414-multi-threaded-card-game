/**
 * This class is responsible for representing
 * a card which the players will use.
 *
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class Card {
  private final int cardNumber;

  /**
  * This returns the card number.
  * 
  * @return The card number.
  */
  public int getCardNumber() {
    return cardNumber;
  }

  /**
  * This method for a card.
  * 
  * @return The card number.
  */
  @Override
  public String toString() {
    return "" + cardNumber;
  }

  /**
  * This is the constructor for a card.
  * 
  * @param cardNumber The number of the card.
  */
  public Card(int cardNumber) {
    this.cardNumber = cardNumber;
  }
}