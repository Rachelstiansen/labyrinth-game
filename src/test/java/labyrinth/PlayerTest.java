package labyrinth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void testMoveUp() {
        Player player = new Player(3,3);
        player.moveUp();
        assertEquals(2, player.getRow());
    }

    @Test
    public void testMoveRight() {
        Player player = new Player(3,3);
        player.moveRight();
        assertEquals(4, player.getCol());
    }
}
