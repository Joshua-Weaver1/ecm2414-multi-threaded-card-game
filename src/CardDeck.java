import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The purpose of this class is used to represent
 * a eeck of cards that the players will add and
 * remove cards too.
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class CardDeck {
  private Card[] deckCards;
  private int numberOfCards;
  private int deckId;

  /**
   * This is the getter for the number of cards in the deck.
   * 
   * @return The number of cards in the deck.
   * @version 1.0
   */
  public int getDeckId() {
    return this.deckId;
  }

  /**
   * Method to return the number of cards in
   * the deck.
   * 
   * @return Number of Cards.
   */
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
      deckCards[i] = deckCards[i + 1];
    }
    return topCard;
  }

  /**
   * writeContentsToFile() will write actions within games (e.g. pickUp card) to
   * .txt file.
   * Each player has a unique file where all of their relevant gae information is
   * stored.
   *
   * @throws IOException with null as the error string message
   */
  public void writeContentsToFile() throws IOException {
    String path = "Deck Output File" + File.separator + "deck" + deckId + "_output.txt";
    StringBuilder output = new StringBuilder("deck" + deckId + " contents: ");
    for (Card card : deckCards) {
      if (card != null) {
        output.append(" ").append(card.getCardNumber());
      }
    }
    File f = new File(path);
    f.getParentFile().mkdirs();
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
    if (!f.createNewFile()) {
      bufferedWriter.write("");
    }
    bufferedWriter.close();
    bufferedWriter = new BufferedWriter(new FileWriter(path, true));
    bufferedWriter.write(output.toString());
    bufferedWriter.newLine();
    bufferedWriter.close();
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
}
