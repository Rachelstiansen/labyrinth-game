package labyrinth;

import java.io.IOException;
import java.util.List;

public class LabyrinthGame {

    private List<String> labyrinth;
    private Player player;

    public void loadMaze(String filename) throws IOException {
        // Kaster IOException hvis filen ikke finnes eller kan leses.
        // Dersom feil, sendes den til kontroller-klassen som håndterer
        // Exception med try/catch
        labyrinth = LabyrinthFileHandler.readLabyrinth(filename);
    }

    public List<String> getLabyrinth() {
        return labyrinth;
    }

    public void createPlayer(int row, int col) {
        // Set starting position
        player = new Player(row, col);
    }

    public Player getPlayer() {
        return player;
    }
}

