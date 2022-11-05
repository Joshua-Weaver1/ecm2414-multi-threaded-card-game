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
        Player[] players = new Player[x];
        // Pack gameCards = new Pack(x, nameOfFile);
        // Card[] packOfCards = gameCards.getPackOfCards();

        //Starting player threads
        for (int i = 1; i <= x; i++) {
            players[i - 1] = new Player(i);
            players[i - 1].run();
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