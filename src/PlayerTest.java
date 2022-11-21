import static org.junit.Assert.assertEquals;
import static.org,junit.AssertArray.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player testPlayer;
    int testPlayerId;
    String testPlayerLocation;
    short cardNumber;
    Card[] exp = new Card[5];
    Card card;

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

    @Test
    public void addCardToPlayerDeckTest() {
        Random rand = new Random();
        Card testCard = new Card(cardNumber);
        cardNumber = (short) rand.nextInt(100);

        this.testPlayer.addCardToPlayerDeck(testCard);
        assertEquals(cardNumber, 5);
    }

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

    @Test
    public void winnerCheckTrueTest() {

        for (int i = 0; i < exp.length; i++) {
            testPlayer.addCardToPlayerDeck(new Card((short) 1));
        }
            assertTrue(this.testPlayer.winnerCheck());
    }

    @Test
    public void winnerCheckFalseTest() {

        for (int i = 0; i < 4; i++) {
            testPlayer.addCardToPlayerDeck(new Card((short) 1));
        }
            assertFalse(this.testPlayer.winnerCheck());
    }

    @Test
    public void announceWinnerTest() {
        for(int i = 0; i < exp.length; i++){
            testPlayer.addCardToPlayerDeck(new Card((short) 1));
        }
        testPlayer.announceWinner((short) 5);
        File playerOutput = new File(this.testPlayerLocation);
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(playerOutput));
            String line1 = bReader.readLine();
            String line2 = bReader.readLine();
            String line3 = bReader.readLine();
            String line4 = bReader.readLine();
            String line5 = bReader.readLine();
            assertEquals("Player " + this.testPlayerId + " created.", line1);
            assertEquals("Player " + this.testPlayerId + " is starting with 1 1 1 1", line2);
            assertEquals("Player 5 has told player " + this.testPlayerId + " that player 5 is the winner.", line3);
            assertEquals("Player " + this.testPlayerId + " stops playing", line4);
            assertEquals("Player " + this.testPlayerId + " cards: 1 1 1 1", line5);
            bReader.close();
        }
        catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}

