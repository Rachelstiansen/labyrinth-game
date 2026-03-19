package labyrinth;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        try {
            FileWriter writer = new FileWriter(HIGHSCORE_FILE, true);
            writer.write(name + "," + time + "\n");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Could not write to file");
            e.printStackTrace();
        }
    }

    public static List<String> readHighscores() throws IOException {
        Path path = Path.of(HIGHSCORE_FILE);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        return Files.readAllLines(path);
    }
    
}
