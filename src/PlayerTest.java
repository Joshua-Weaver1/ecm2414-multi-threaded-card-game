import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.lang.reflect.Field;

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
        this.testPlayerLocation = "Logs" + File.separator + "player" + this.testPlayerId + "_output.txt";
        this.testPlayer = new Player(this.testPlayerId);
    }

    @Test
    public void getPlayerIdTest() {
        assertEquals("Incorrect player id", this.testPlayerId, this.testPlayer.getPlayerId());
    }

    @Test
    public void addCardToPlayerDeck() {
        card = new Card(testPlayer.getPlayerId());
        this.testPlayer.addCardToPlayerDeck(card);
        assertEquals( 1,this.testPlayer.getNumberOfCards());
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
    @Test
    public void makeMoveTest() {
        Card takeCard = new Card((short) rand.nextInt(100)), card2, card4;

        do{
            card2 = new Card((short) rand.nextInt(100));
        }while(card2.equals(takeCard));

        do{
            card4 = new Card((short) rand.nextInt(100));
        }
        while(card4.equals(takeCard) || card4.equals(card2));

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

