import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  Player testPlayer;
  int testPlayerId;
  String testPlayerLocation;
  short cardNumber;
  Card card;
  Card[] exp = new Card[5];
  Random rand = new Random();

  @Before
  public void initialise() {

    this.testPlayerId = (short) Math.round(Math.random() * 100);
    this.testPlayerLocation = "Logs" + File.separator + "player" + this.testPlayerId +
      "_output.txt";
    this.testPlayer = new Player(this.testPlayerId);
  }

  // A method that checks if the cards are the same
  private static boolean allEqual() {
    Card[] cards = new Card[5];
    if (cards == null || cards.length == 0) {
      return false;
    }
    for (int i = 0; i < cards.length; i++) {
      if (cards[0] != cards[i]) {
        return false;
      }
    }
    return true;
  }

  // Tests whether the correct player id is returned
  @Test
  public void getPlayerIdTest() {
    assertEquals("Incorrect player id", this.testPlayerId, this.testPlayer.getPlayerId());
  }

  // Tests whether a card is added to the player's hand
  @Test
  public void addCardToPlayerDeck() {
    card = new Card(testPlayer.getPlayerId());
    this.testPlayer.addCardToPlayerDeck(card);
    assertEquals(1, this.testPlayer.getNumberOfCards());
  }

  // Tests whether the the correct output is outputted to the player's log file
  @Test
  public void playerOutputTest() {
    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testPlayerLocation,
          true));
      bufferedWriter.write("Test");
      bufferedWriter.close();
    } catch (Exception e) {
      System.out.println("Error writing to file");
    }
  }

  // Tests whether a player has won the game (have won)
  @Test
  public void winnerCheckTrueTest() {

    for (int i = 0; i < exp.length; i++) {
      testPlayer.addCardToPlayerDeck(new Card((short) 1));
    }
    assertTrue(this.testPlayer.winnerCheck());
  }

  // Tests whether a player has won the game (have not won)
  @Test
  public void winnerCheckFalseTest() {
    for (int i = 0; i < 4; i++) {
      testPlayer.addCardToPlayerDeck(new Card((short) 1));
    }
    if (allEqual() == false) {
      assertFalse(this.testPlayer.winnerCheck());
    }

  }

  // Tests whether the winner announces that they have won
  @Test
  public void announceWinnerTest() {
    for (int i = 0; i < exp.length; i++) {
      testPlayer.addCardToPlayerDeck(new Card((short) 1));
    }
    testPlayer.announceWinner((short) 5);
    File playerOutput = new File(this.testPlayerLocation);
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(playerOutput));

      String line1 = bufferedReader.readLine();
      String line2 = bufferedReader.readLine();
      String line3 = bufferedReader.readLine();
      String line4 = bufferedReader.readLine();
      String line5 = bufferedReader.readLine();

      assertEquals("Player " + this.testPlayerId + " created.", line1);
      assertEquals("Player " + this.testPlayerId + " is starting with 1 1 1 1", line2);
      assertEquals("Player 5 has told player " + this.testPlayerId 
          + " that player 5 is the winner.", line3);
      assertEquals("Player " + this.testPlayerId + " stops playing", line4);
      assertEquals("Player " + this.testPlayerId + " cards: 1 1 1 1", line5);
      bufferedReader.close();
    } catch (IOException e) {
      System.out.println("Error reading file");
    }
  }

  // Tests whether the player has drawn a card and also discarded a useless card
  // and kept a useful card
  @Test
  public void makeMoveTest() {
    Card takeCard = new Card((short) rand.nextInt(100));

    Card card2;
    Card card4;
    do {
      card2 = new Card((short) rand.nextInt(100));
    } while (card2.equals(takeCard));

    do {
      card4 = new Card((short) rand.nextInt(100));
    } while (card4.equals(takeCard) || card4.equals(card2));

    if (card2 != card4) {
      testPlayer.addCardToPlayerDeck(new Card(testPlayer.getPlayerId()));
      testPlayer.addCardToPlayerDeck(card2);
      testPlayer.addCardToPlayerDeck(new Card(testPlayer.getPlayerId()));
      testPlayer.addCardToPlayerDeck(card4);

      int useless = rand.nextInt(100);
      int useful = rand.nextInt(100);
      Card uselessCard = this.testPlayer.makeMove(takeCard, useless, useful);

      assertNotEquals(this.testPlayer.getPlayerId(), uselessCard.getCardNumber());
    }

    try {
      Field getPlayerCards = Player.class.getDeclaredField("hand");
      getPlayerCards.setAccessible(true);
      Card[] correctHand = (Card[]) getPlayerCards.get(testPlayer);

      assertTrue(correctHand[1] == takeCard || correctHand[3] == takeCard);

    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }
}