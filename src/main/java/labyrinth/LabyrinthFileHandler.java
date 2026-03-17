package labyrinth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LabyrinthFileHandler {

    private static final String HIGHSCORE_FILE = "highscores.txt";
    
    public static List<String> readLabyrinth(String filename) throws IOException {
        // Kaster IOException hvis filen ikke finnes eller kan leses.
        // Dersom feil, sendes den til kontroller-klassen som håndterer
        // Exception med try/catch
        return Files.readAllLines(Path.of(filename));
    }

    public static void writeHighscore(String name, long time) throws IOException {

    }

    public static List<String> readHighscores() throws IOException {
        
    }
    
}
