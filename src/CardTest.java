import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CardTest {
    Card testCard;
    int testCardValue;

    @Before
    public void initialise() {
        this.testCardValue = (int) Math.round(Math.random() * 100);
        this.testCard = new Card(this.testCardValue);
    }

    @Test
    public void getCardNumber() {
        assertEquals(this.testCard.getCardNumber(), testCardValue);
    }


    @Test
    public void test() {
        assertEquals("Hello!", "Hello!");
    }
}