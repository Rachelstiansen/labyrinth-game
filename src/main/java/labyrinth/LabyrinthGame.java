package labyrinth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LabyrinthGame {
    private List<String> labyrinth;
    private Player player;

    public void loadMaze(String filename) throws IOException {
        // Kaster IOException hvis filen ikke finnes eller kan leses.
        // Dersom feil, sendes den til kontroller-klassen som håndterer
        // Exception med try/catch
        labyrinth = Files.readAllLines(Path.of(filename));
    }

    public List<String> getLabyrinth() {
        return labyrinth;
    }

    public Player createPlayer() {
        // Set starting position
        return this.player;
    }
}

