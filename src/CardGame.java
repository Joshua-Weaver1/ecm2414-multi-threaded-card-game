import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is the executable CardGame class.
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */

public class CardGame {

    public static void startSimulation(String nameOfFile, int x) throws IOException {
        //Variables
        Player[] players = new Player[x];
        Pack gameCards = new Pack(x, nameOfFile);
        Card[] packOfCards = gameCards.getPackOfCards();
        CardDeck[] decks = new CardDeck[x];
        boolean hasPlayerWon = false;
        int winner = -1;
        int turns = 0;

        //Starting player threads
        for (int i = 1; i <= x; i++) {
            players[i - 1] = new Player(i);
            players[i - 1].run();
            decks[i - 1] = new CardDeck(i);
        }

        // Give deck their cards
        for (int i = 0; i < x*4; i++) {
            decks[i % x].addToDeck(packOfCards[i]);
        }

        // Give players their cards
        for (int i = 0; i < x*4; i++) {
            players[i % x].addCardToDeck(packOfCards[i]);
        }

        for (int i = 0; i < x; i++) {
            if (players[i].startGameCheck()) {
                hasPlayerWon = true;
                players[i].playerOutput("Player " + players[i].getPlayerId() + " is the winner!");
                if(hasPlayerWon == true) {
                    break;
                }
            }
        }

        while (!hasPlayerWon) {
            int playersTurn = turns++ % x;
            int discardToDeck = (playersTurn + 1) % x;
            int pickUpFromDeck = playersTurn;

            synchronized (players[playersTurn]) {
                decks[discardToDeck].addCardToRight(players[playersTurn].takeTurn(decks[pickUpFromDeck].drawCardFromLeft(), discardToDeck, pickUpFromDeck)
                );
            }

            // checks if player has won at the end of every turn
            if (players[playersTurn].hasWon()) {
                hasPlayerWon = true;
                winner = players[playersTurn].getPlayerId();
            }
        }

        for (short i = 0; i < x; i++) {
            synchronized (players[i]) {
                players[i].informPlayerHasWon(winner);
            }
        }
    }

    public static void main(String args[]) {

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the number of desired players here: ");
            int x = Integer.parseInt(bReader.readLine());
            System.out.println("Enter the filename of the desired pack here: ");
            String nameOfFile = bReader.readLine();

            System.out.println("The number of players you have selected are: " + x);
            System.out.println("The name of the file you have selected is: " + nameOfFile);

            startSimulation(nameOfFile, x);


        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}