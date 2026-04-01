package labyrinth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabyrinthGameTest {
    private LabyrinthGame game;
    private static final String TEST_FILE = "src/main/resources/labyrinth/labyrinth.txt";

    @BeforeEach
    public void setUp() {
        game = new LabyrinthGame();
        try {
            game.loadLabyrinth(TEST_FILE);
        } 
        catch (IOException e) {
            fail("IOException should not occur" + e.getMessage());
        }
    }

    @Test
    public void testCannotMoveOutsideGrid() {
        assertFalse(game.canMove(game.getLabyrinth().size(), 0), "Tests if player cannot move outside the grid downwards");
        int row = 0;
        String line = game.getLabyrinth().get(row);
        assertFalse(game.canMove(line.length(), 0), "Tests if player cannot move outside the grid to the right");
    }

    @Test
    public void testPlayerMovesRight() {

        game.createPlayer(3, 3);
        int startCol = game.getPlayer().getCol();
        game.moveRight();
        int newCol = game.getPlayer().getCol();

        assertEquals(startCol + 1, newCol, "Tests if player moves right by one column");
    }

    @Test
    public void testPlayerCannotMoveThroughWall() {
        
        for (int row = 0; row < game.getLabyrinth().size(); row++) {
            String line = game.getLabyrinth().get(row);
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) == '#') {
                    assertFalse(game.canMove(row, col), "Tests if player cannot move through wall at position (" + row + ", " + col + ")");
                }
            }
        }
    }

    @Test
    public void testPlayerCannotStartInWall() {
        for (int row = 0; row < game.getLabyrinth().size(); row++) {
            String line = game.getLabyrinth().get(row);
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) == '#') {
                    int r = row;
                    int c = col;
                    assertThrows(IllegalArgumentException.class, () -> {
                        game.createPlayer(r, c);
                    }, "Tests if creating player in wall at position (" + r + ", " + c + ") throws IllegalArgumentException");
                }
            }
        }
    }
}
