import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is class handles all the actions for the packs such as creating a pack with 
 * the cards within the file. 
 * 
 * @author Kevin Liu & Joshua Weaver
 * @version 1.0
 */
public class Pack {

    //Assigning new pack of cards variable to Pack
    private Card[] packOfCards;

    /**
     * This is returns the pack of cards.
     * 
     * @return The pack of cards.
     */
    public Card[] getPackOfCards() {
        return packOfCards;
    }

    /**
     * This method outputs the information about the pack.
     * 
     * @param nameOfFile The name of the file.
     * @param x The number of players.
     * @throws IOException The exception for the file.
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
                    System.out.println("The file exceeded the maximum amount of cards for the game!");
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
            System.out.println("There was a number format exception of the pack file!");
        }
    }
}
