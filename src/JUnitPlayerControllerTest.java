import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Player;

import static org.junit.Assert.*;

public class JUnitPlayerControllerTest {

    PlayerController pController;
    Player p1;
    Player p2;

    @org.junit.Before
    public void setUp() throws Exception {
        pController = PlayerController.getInstance();
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        pController.addPlayer(p1);
        pController.addPlayer(p2);
    }

    @org.junit.Test
    public void nextPlayer() {
        assertEquals(p2,pController.nextPlayer());
    }

    @org.junit.Test
    public void putInJail() {
        pController.putInJail();
        assertTrue(pController.getCurrentPlayer().isInJail());
    }

    @org.junit.Test
    public void getOutFromJail() {
        pController.getOutFromJail();
        assertFalse(pController.getCurrentPlayer().isInJail());
    }

    @org.junit.Test
    public void movePlayer() {
        int targetPosition = 7;
        pController.movePlayer(targetPosition);
        assertEquals(targetPosition, pController.getCurrentPlayer().getTargetPosition());
    }
}