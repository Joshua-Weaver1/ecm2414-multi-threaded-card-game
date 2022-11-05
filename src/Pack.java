import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Pack class.
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */

public class Pack {

    //Assigning new pack of cards variable to Pack
    private Card[] packOfCards;

    /**
     * Getter method for pack of cards.
     * 
     * @return The pack of cards.
     */
    public Card[] getPackOfCards() {
        return packOfCards;
    }

    /**
     * Constructor
     * 
     * @param x
     * @param nameOfFile
     * @throws IOException
     */
    public Pack(int x, String nameOfFile) throws IOException {

        try {
            //Attributes
            int positionInList = 0;
            this.packOfCards = new Card[x*8];
            int requiredAmount = x*8;
            String lineOfFile;
            File userInputFile = new File(nameOfFile);
            BufferedReader bReader = new BufferedReader(new FileReader(userInputFile));

            //Add cards from file to the pack of cards
            while ((lineOfFile = bReader.readLine()) != null) {
                //Check for too many cards
                if (positionInList == x*8) {
                    System.out.println("The file exceeded the maximim amount of cards for the game!");
                } else {
                    //Reads line and adds card
                    this.packOfCards[positionInList++] = new Card((int) Integer.parseInt(lineOfFile));
                }
            }

            //Check for too few cards
            int cardsAdded = positionInList;
            if (cardsAdded < requiredAmount) {
                System.out.println("The file entered did not contain a sufficient amount of cards!");
            }

            //Closes buffered reader
            bReader.close();

        } catch (NumberFormatException e) {
            System.out.println("There was a number format exception if the pack file!");
        }
    }
}
