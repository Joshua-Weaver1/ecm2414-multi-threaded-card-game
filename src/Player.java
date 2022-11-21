import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * This is the executable CardGame class.
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class Player implements Runnable{

    private final int playerId;
    private int numberOfcards;
    private final Card[] playerCards;
    private final String location;

    /**
     * This is a method that notifies the user that the threading has started and
     * 
     * @version 1.0
     */
    @Override
    public void run() {
        System.out.println("Thread of player " + this.playerId + " has started.");
    }

    /**
     * This is the constructor for a player and also creates a log for 
     * the players actions within the game.
     * 
     * @param playerId The id of the player.
     */
    public Player(int playerId) {
        this.playerId = playerId;
        this.playerCards = new Card[5];
        this.numberOfcards = 0;
        this.location = "Logs" + File.separator + "player" + this.playerId + "_output.txt";
        try {
            File playerOutFile = new File(this.location);
            playerOutFile.getParentFile().mkdirs();
            if (!playerOutFile.createNewFile()) {
                BufferedWriter bwriter = new BufferedWriter(new FileWriter(location));
                bwriter.write("Player " + this.playerId + " created.");
                bwriter.close();
            }
        } catch (IOException e) {
            System.out.printf("An output file for player"+ playerId + " has not been created.");
        }
    }

    /**
     * This is the getter for the player id.
     * 
     * @return The player id.
     * @version 1.0
     */
    public int getPlayerId(){
        return this.playerId;
    }

    /**
     * This is the getter for the player cards and it outputs a string representation
     * of the player's cards.
     * 
     * @return The player cards.
     * @version 1.0
     */
    public void addCardToPlayerDeck(Card card) {
        this.playerCards[numberOfcards++] = card;
        if (numberOfcards == 4) {
            playerOutput("Player " + this.playerId + " is starting with " + this.playerCards[0] + " " 
            + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3]);
        }
    }

    /**
     * This is the method that outputs a string to the player's log file.
     * 
     * @param string The string to be outputted.
     * @version 1.0
     */
    public void playerOutput(String string) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(location, true));
            bWriter.newLine();
            bWriter.write(string);
            bWriter.close();
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }

    /**
     * This is the method that checks the players' cards before players start drawing cards
     * to see if a player has pulled 4 cards of the same number.
     * 
     * @return True if a player has 4 cards of the same number.
     * @version 1.0
     */
    public boolean startGameCheck() {
        int cardPos = 0;
        Card card1 = this.playerCards[0];
        for(Card temp : this.playerCards) {
            if(cardPos++ == 4) break;
            if(temp.getCardNumber() != card1.getCardNumber()) {
                return false;
            }
        }
        System.out.println("Player " + playerId + " is the winner straight away!");
        return true;
    }

    /**
     * This is the method that controls the actions of the player when it is their turn
     * to draw a card.
     * 
     * @param cardSelected The card that the player has selected.
     * @param rightDeck The deck to the right of the player.
     * @param leftDeck  The deck to the left of the player.
     * @return currentCard The card that the player has drawn.
     * @version 1.0
     */
    public Card makeMove(Card cardSelected, int rightDeck, int leftDeck) {
        Random randomChoice = new Random();
        boolean wantedCard = true;
        Card currentCard = new Card(1);
        int positionOfCardToSwitch = 0;

        while (wantedCard == true) {
            positionOfCardToSwitch = (int) randomChoice.nextInt(4);
            currentCard = this.playerCards[positionOfCardToSwitch];
            if (currentCard.getCardNumber() != playerId) {
                wantedCard = false;
            }
        }

        // Increase deck number
        leftDeck++;
        rightDeck++;

        this.playerCards[positionOfCardToSwitch] = cardSelected;

        playerOutput("Player " + this.getPlayerId() + " draws " + cardSelected.getCardNumber() + " from deck " + leftDeck);
        playerOutput("Player " + this.playerId + " discards " + currentCard.getCardNumber() + " to deck " + rightDeck);
        playerOutput("Player " + this.playerId + " current hand " + this.playerCards[0] + " " 
        + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3]);

        return currentCard;
    }

    /**
     * This is the method that checks if a player has won
     * 
     * @return True if a player has won.
     * @version 1.0
     */
    public boolean winnerCheck() {
        Card firstCard = this.playerCards[0];
        int counter = 0;
        for (Card card :
                this.playerCards) {
            if (counter++ == 4) break;
            if (card.getCardNumber() != firstCard.getCardNumber()) return false;
        }
        System.out.println("Player " + this.getPlayerId() + " is the winner of the game!");
        return true;
    }

    /**
     * This is the method that announces to the other players that a player has won the game
     * by collecting 4 cards of the same number.
     * 
     * @param playerId The id of the player
     * @version 1.0
     */
    public void announceWinner(int playerId) {
        StringBuilder sBuilder = new StringBuilder();
        if (playerId == this.playerId) {
            sBuilder.append("Player ").append(playerId).append(" has won the game!");
        } else {
            sBuilder.append("Player ").append(playerId).append(" has told player ")
                    .append(this.playerId).append(" that player ").append(playerId).append(" is the winner.");
        }
        playerOutput(sBuilder.toString());
        playerOutput("Player " + this.playerId + " stops playing");

        StringBuilder showCards = new StringBuilder("Player ").append(this.playerId).append(' ');
        if (playerId == this.playerId) {
            showCards.append("winning ");
        }
        showCards.append("cards: ").append(this.playerCards[0] + " " + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3]);
        playerOutput(showCards.toString());
    }

    public Card[] getCards() {
        return playerCards;
    }
}
