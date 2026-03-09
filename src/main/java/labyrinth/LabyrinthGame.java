package labyrinth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LabyrinthGame {
    private List<String> labyrinth;
    
    public void loadMaze(String filename) throws IOException {
        labyrinth = Files.readAllLines(Path.of(filename));
    }

    public List<String> getLabyrinth() {
        return labyrinth;
    }
}

