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
        this.packOfCards = new Card[x * 8];
    }
}
