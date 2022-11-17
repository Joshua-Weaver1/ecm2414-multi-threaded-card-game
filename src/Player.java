import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * PLayer Class.
 * 
 * @author Kevin Liu & Joshua Weaver
 */
public class Player implements Runnable{

    private final int playerId;
    private int numberOfcards;
    private final Card[] playerCards;
    private final String location;

    @Override
    public void run() {
        System.out.println("Thread of player " + this.playerId + " has started.");
    }

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

    public int getPlayerId(){
        return this.playerId;
    }

    public void addCardToPlayerDeck(Card card) {
        this.playerCards[numberOfcards++] = card;
        if (numberOfcards == 4) {
            playerOutput("Player " + this.playerId + " is starting with " + this.playerCards[0] + " " 
            + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3]);
        }
    }

    private String handStringRepr() {
        return this.playerCards[0] + " " + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3];
    }

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

    public boolean startGameCheck() {
        int cardPos = 0;
        Card card1 = this.playerCards[0];
        for(Card temp : this.playerCards) {
            if(cardPos++ == 4) break;
            if(temp.getCardNumber() != card1.getCardNumber()) {
                return false;
            }
        }
        System.out.println("Player " + playerId + " is the winner!");
        return true;
    }

    public Card takeTurn(Card pickUp, int discardDeckNumber, int pickUpDeckNumber) {
        boolean isPreferred = true;
        Random picker = new Random();
        Card currentCard;
        int swapIndex;
        do {
            swapIndex = (int) picker.nextInt(4);
            currentCard = this.playerCards[swapIndex];
            if (currentCard.getCardNumber() != playerId) isPreferred = false;
        } while (isPreferred);

        // Increment deck number for text file.
        pickUpDeckNumber++;
        discardDeckNumber++;

        this.playerCards[swapIndex] = pickUp; // Add the picked up card to hand.

        playerOutput("Player " + this.getPlayerId() + " draws " + pickUp.getCardNumber() + " from deck " + pickUpDeckNumber);
        playerOutput("Player " + this.playerId + " discards " + currentCard.getCardNumber() + " to deck " + discardDeckNumber);
        playerOutput("Player " + this.playerId + " current hand " + this.playerCards[0] + " " 
        + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3]);

        return currentCard;
    }

    public boolean hasWon() {
        Card firstCard = this.playerCards[0];
        int counter = 0;
        for (Card card :
                this.playerCards) {
            if (counter++ == 4) break;
            if (card.getCardNumber() != firstCard.getCardNumber()) return false;
        }
        System.out.printf("Player %d has won%n", playerId);
        return true;
    }

    public void informPlayerHasWon(int playerId) {
        StringBuilder winOutput = new StringBuilder();
        // check if player number is self
        if (playerId == this.playerId) {
            winOutput.append("player ").append(playerId).append(" wins");
        } else {
            winOutput.append("player ").append(playerId).append(" has informed player ")
                    .append(this.playerId).append(" that player ").append(playerId).append(" has won");
        }
        playerOutput(winOutput.toString());
        playerOutput("player " + this.playerId + " exits");

        StringBuilder handOutput = new StringBuilder("player ").append(this.playerId).append(' ');
        if (playerId == this.playerId) {
            handOutput.append("final ");
        }
        handOutput.append("hand: ").append(this.playerCards[0] + " " 
        + this.playerCards[1] + " " + this.playerCards[2] + " " + this.playerCards[3]);
        playerOutput(handOutput.toString());
    }
}
