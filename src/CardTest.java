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

  // Tests whether the correct card number is returned
  @Test
  public void getCardNumber() {
    assertEquals(this.testCard.getCardNumber(), testCardValue);
  }

  // Tests whether the correct string is output with the correct
  // formatting and card number
  @Test
  public void toStringTest() {
    assertEquals(this.testCard.toString(), "" + testCardValue);
  }
}