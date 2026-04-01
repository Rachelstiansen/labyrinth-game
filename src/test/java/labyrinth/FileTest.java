package labyrinth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileTest {
    @Test
    public void testWriteAndReadHighscore() {
        try {
            LabyrinthFileHandler.clearHighscores(); // fjerner tidligere entries
            LabyrinthFileHandler.writeHighscore("TestUser", 18); 
            List<String> highscores = LabyrinthFileHandler.readHighscores();
            
            assertFalse(highscores.isEmpty());
            assertEquals(highscores.get(highscores.size() - 1), "TestUser, 18");
        }
        catch (IOException e) {
            fail("IOException should not occur" + e.getMessage());
        }
    }
}
