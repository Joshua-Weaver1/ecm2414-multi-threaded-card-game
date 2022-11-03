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

    public static void main(String args[]) {

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the number of desired players here: ");
            int x = Integer.parseInt(bReader.readLine());
            System.out.println("Enter the filename of the desired pack here: ");
            String nameOfFile = bReader.readLine();

            System.out.println(x);
            System.out.println(nameOfFile);


        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void startSimulation(String nameOfFile, int x) throws IOException {
        Pack packOfCards = new Pack(x, nameOfFile);
    }
}