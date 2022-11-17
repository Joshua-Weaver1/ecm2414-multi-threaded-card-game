import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {
    @Test
    public void getPlayerIdTest() {
        Player player = new Player(1);
        assertEquals("Incorrect player id", 1, player.getPlayerId());
    }
    
}
