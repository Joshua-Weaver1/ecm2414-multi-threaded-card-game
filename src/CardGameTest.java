import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CardGameTest {

    @Test
    public void startSimulationTest(){
        try{
            int x = (int) Math.round(Math.random() * 100)+1;
            String nameOfFile = "pack.txt";
            File playerOutput = new File(nameOfFile);
            BufferedWriter writer;
            if (!playerOutput.createNewFile()) {
                writer = new BufferedWriter(new FileWriter(nameOfFile));
                writer.write("");        
                writer.close();
            }
            writer = new BufferedWriter(new FileWriter(nameOfFile, true));
            for (int i = 1; i <= x; i++) {
                for (int j = 0; j < 8; j++) {
                    writer.write(i + "");
                    writer.newLine();
                }
            }
            writer.close();
            try {
                // runs the game with the newly created pack file
                CardGame.startSimulation(nameOfFile, x);
                // if the game throws any errors while running, this test will fail
            } catch (IOException e) {
                fail("Test failed due to game play function throwing error");
            }

        } catch (IOException e) {
            // this is an error with the test, not the actual code base of the game
            System.out.println("The test suite failed to build a test pack to run the game with");
        }
    }   
}