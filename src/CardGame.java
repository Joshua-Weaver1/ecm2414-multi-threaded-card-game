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
            int n = Integer.parseInt(bReader.readLine());
            System.out.println("Enter the filename of the desired pack here: ");
            String filename = bReader.readLine();


        } catch (IOException e) {
            System.out.println("exception: " + e);
        }
    }
}