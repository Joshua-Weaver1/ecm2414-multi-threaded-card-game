import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The purpose of this class is used to represent
 * a deck of cards that the players will add and
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
   * This is the method to get the number of cards in the deck.
   * 
   * @return The number of cards in the deck.
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
   */
  public void addToDeck(Card card) {
    this.deckCards[numberOfCards++] = card;
  }

  /**
   * This is the method to draw a card from the left of the deck.
   * 
   * @return The card drawn.
   */
  public Card drawCardFromLeft() {
    Card topCard = this.deckCards[0];
    for (int i = 0; i <= 3; i++) {
      deckCards[i] = deckCards[i + 1];
    }
    return topCard;
  }

  /**
   * This method produces output files for the decks at
   * the end of the game.
   *
   * @throws IOException IOException.
   */
  public void fileOutput() throws IOException {
    String location = "Deck Output File" + File.separator + "deck" + deckId + "_output.txt";
    StringBuilder stringBuilder = new StringBuilder("Deck" + deckId + " final contents: ");
    for (Card x : deckCards) {
      if (x != null) {
        stringBuilder.append(" ").append(x.getCardNumber());
      }
    }

    File outFile = new File(location);
    outFile.getParentFile().mkdirs();
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(location));
    if (!outFile.createNewFile()) {
      bufferedWriter.write("");
    }
    bufferedWriter.close();
    bufferedWriter = new BufferedWriter(new FileWriter(location, true));
    bufferedWriter.write(stringBuilder.toString());
    bufferedWriter.newLine();
    bufferedWriter.close();
  }

  /**
   * This is the method to add a card to the right of the deck.
   * 
   * @param card The card to be added.
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
   */
  CardDeck(int deckId) {
    this.numberOfCards = 0;
    this.deckId = deckId;
    this.deckCards = new Card[5];
  }
}
