package labyrinth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HighscoreTest {
    @Test
    public void testCompareToBetterTime() {
        Highscore h1 = new Highscore("Ivar", 18);
        Highscore h2 = new Highscore("Miriam", 22);
        assertTrue(h1.compareTo(h2) < 0, "Tests if highscore gets sorted in ascending order based on time");
    }
}
