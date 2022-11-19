import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player testPlayer;
    int testPlayerId;
    String testPlayerLocation;

    @Before
    public void initialise() {
        this.testPlayerId = (int) Math.round(Math.random() * 100);
        this.testPlayerLocation = "Logs" + File.separator + "player" + this.testPlayerId + "_output.txt";
        this.testPlayer = new Player(this.testPlayerId);
    }

    @Test
    public void getPlayerIdTest() {
        assertEquals("Incorrect player id", this.testPlayerId, this.testPlayer.getPlayerId());
    }

    // @Test
    // public void addCardToPlayerDeckTest() {
    //     Card testCard = new Card(1);
    //     CardDeck testDeck = new CardDeck(1);
    //     this.testPlayer.addCardToPlayerDeck(testCard);
    //     assertEquals("Card was not added to player deck", 1, this.testPlayer.getNumberOfCards());
    // }

    @Test
    public void playerOutputTest() {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(this.testPlayerLocation, true));
            bWriter.write("Test");
            bWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }
}

