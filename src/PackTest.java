import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Before;
import org.junit.Test;

public class PackTest {
  Pack testPack;
  int testPackSize;
  Card[] testPackArray;

  
  @Before
  public void initialise() throws Exception {
    testPackSize = (int) Math.round(Math.random() * 100);
    String nameOfFile = "pack.txt";
    File playerOutput = new File(nameOfFile);
    BufferedWriter output;

    if (!playerOutput.createNewFile()) {
      output = new BufferedWriter(new FileWriter(nameOfFile));
      output.write("");
      output.close();
    }

    testPackArray = new Card[testPackSize * 8];
    int nextCard = 0;

    output = new BufferedWriter(new FileWriter(nameOfFile, true));
    for (int i = 1; i <= testPackSize; i++) {
      for (int j = 0; j < 8; j++) {
        testPackArray[nextCard++] = new Card((short) i);
        output.write(i + "");
        output.newLine();
      }
    }
    output.close();
    testPack = new Pack(testPackSize, nameOfFile);
  }

  // Tests whether the pack is the correct size
  @Test 
  public void getPackOfCardsTest() {
    assertTrue(this.testPack.getPackOfCards().length == testPackArray.length);
  }
}