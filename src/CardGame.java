import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is the executable CardGame class.
 * It contains the threading required for the game
 * to run successfully.
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class CardGame {

  /**
   * This is method starts the simulaition for the card game.
   * 
   * @param nameOfFile The name of the file.
   * @param x          The number of players.
   * @throws IOException The exception for the file.
   */
  public static void startSimulation(String nameOfFile, int x) throws IOException {

    Player[] players = new Player[x];
    Pack gameCards = new Pack(x, nameOfFile);
    Card[] packOfCards = gameCards.getPackOfCards();
    CardDeck[] decks = new CardDeck[x];
    boolean hasPlayerWon = false;
    int victor = 0;
    int attempts = 0;

    // Creating players
    for (int i = 1; i <= x; i++) {
      players[i - 1] = new Player(i);
      // Starting player threads
      players[i - 1].run();
      // Creating decks
      decks[i - 1] = new CardDeck(i);
    }

    // Give deck their cards
    for (int i = 0; i < x * 4; i++) {
      decks[i % x].addToDeck(packOfCards[i]);
    }

    // Give players their cards
    for (int i = 0; i < x * 4; i++) {
      players[i % x].addCardToPlayerDeck(packOfCards[i]);
    }

    // Checks the player's cards to see if they have been given
    // a winning hand
    for (int i = 0; i < x; i++) {
      if (players[i].startGameCheck()) {
        hasPlayerWon = true;
        players[i].playerOutput("Player " + players[i].getPlayerId() + " is the winner!");
        if (hasPlayerWon == true) {
          break;
        }
      }
    }

    // If no player has won, the game continues
    while (!hasPlayerWon) {
      int memberAttempts = attempts++ % x;
      int leftDeck = memberAttempts;
      int rightDeck = (memberAttempts + 1) % x;

      synchronized (players[memberAttempts]) {
        decks[rightDeck].addCardToRight(players[memberAttempts].makeMove(
            decks[leftDeck].drawCardFromLeft(), rightDeck, leftDeck));
      }

      // checks if player has won at the end of every turn
      if (players[memberAttempts].winnerCheck()) {
        hasPlayerWon = true;
        victor = players[memberAttempts].getPlayerId();
      }
    }

    // Announces the winner
    for (int i = 0; i < x; i++) {
      synchronized (players[i]) {
        players[i].announceWinner(victor);
      }
      decks[i].fileOutput();
    }
  }

  /**
   * This is the main method for the game.
   * 
   * @param args The arguments for the game.
   * @version 1.0
   */
  public static void main(String[] args) {

    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Enter the number of desired players here: ");
      int x = Integer.parseInt(bufferedReader.readLine());
      System.out.println("Enter the filename of the desired pack here: ");
      String nameOfFile = bufferedReader.readLine();

      System.out.println("The number of players you have selected are: " + x);
      System.out.println("The name of the file you have selected is: " + nameOfFile);

      startSimulation(nameOfFile, x);

      bufferedReader.close();

    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }
}