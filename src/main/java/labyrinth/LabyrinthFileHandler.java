package labyrinth;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// Håndterer fillesing og filskriving for labyrinten og highscores
public class LabyrinthFileHandler {

    private static final String HIGHSCORE_FILE = "src/main/resources/labyrinth/highscores.txt";
    
    public static List<String> readLabyrinth(String filename) throws IOException {
        // Kaster IOException hvis filen ikke finnes eller kan leses.
        // Dersom feil, sendes den til kontroller-klassen som håndterer
        // Exception med try/catch
        return Files.readAllLines(Path.of(filename));
    }

    public static void writeHighscore(String name, long time) throws IOException {
        // Skriver ny highscore med navn og tid til highscores.txt
        try {
            FileWriter writer = new FileWriter(HIGHSCORE_FILE, true);
            writer.write(name + "," + time + "\n"); // en highscore per linje
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Could not write to file");
        }
    }

    public static List<String> readHighscores() throws IOException {
        // Leser alle highscores fra highscores.txt
        Path path = Path.of(HIGHSCORE_FILE);
        if (!Files.exists(path)) {
            // Hvis filen ikke finnes, returner en tom liste
            return new ArrayList<>();
        }
        return Files.readAllLines(path); // leser alle linjene i filen
    }

    public static void clearHighscores() throws IOException{
        // sletter alle highscores ved å overskrive filen med en tom fil
        FileWriter writer = new FileWriter(HIGHSCORE_FILE, false);
        writer.close();
    }

}
