package labyrinth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// holder styr på labyrinten, spilleren, spillstatus og highscores
public class LabyrinthGame {

    private List<String> labyrinth; // en streng = en rad i labyrinten
    private Player player;
    private boolean gameOver; // angir om spillet er over
    private long startTime;   // starttid i millisekunder
    private long endTime;     // sluttid i millisekunder


    public LabyrinthGame() {
        // Setter gameOver til false i konstruktøren
        this.gameOver = false;
    }

    public void startTimer() {
        // starter timer ved å lagre nåværende tid 
        startTime = System.currentTimeMillis();
    }

    public void loadLabyrinth(String filename) throws IOException {
        // Kaster IOException hvis filen ikke finnes eller kan leses.
        // Dersom feil, sendes den til kontroller-klassen som håndterer
        // Exception med try/catch
        labyrinth = LabyrinthFileHandler.readLabyrinth(filename);
    }

    public List<String> getLabyrinth() {
        return labyrinth;
    }

    public void createPlayer(int row, int col) {
        // oppretter spiller på en gitt startposisjon
        player = new Player(row, col);
    }

    public Player getPlayer() {
        return player;
    }

    public char getCurrentTile() {
        // returnerer char på ruten spilleren står på
        return labyrinth.get(player.getRow()).charAt(player.getCol());
    }

    public void checkGameOver() {
        // spillet er ferdig om spilleren har nådd mål (finish: F)
        if (getCurrentTile() == 'F') {
            gameOver = true;
            endTime = System.currentTimeMillis(); // lagrer sluttiden
        }
    }

    public long getTimeUsed() {
        // returnerer tiden brukt i sekunder
        if (!gameOver) {
            return (System.currentTimeMillis() - startTime) / 1000;
        }
        return (endTime - startTime) / 1000;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean canMove(int row, int col) {
        // sjekker om spilleren kan flytte til en gitt posisjon
        // eller om det er en vegg der, spilleren kan ikke gå gjennom veggen
        if (row < 0 || row >= labyrinth.size()) {
            // sjekker at vi ikke går utenfor labyrinten
            return false;
        }
        if (col < 0 || col >= labyrinth.get(0).length()) {
            // sjekker at vi ikke går utenfor labyrinten
            return false;
        }
        // returnerer false om posisjonen vi vil flytte til er en vegg, ellers true
        return labyrinth.get(row).charAt(col) != '#';
    }

    public void moveUp() {
        // flytter spilleren opp om mulig
        if (canMove(player.getRow() - 1, player.getCol())) {
            player.moveUp();
            checkGameOver(); // sjekker om vi har nådd mål
        }
    }
    
    public void moveDown() {
        // flytter spilleren ned om mulig
        if (canMove(player.getRow() + 1, player.getCol())) {
            player.moveDown();
            checkGameOver(); // sjekker om vi har nådd mål
        }
    }

    public void moveLeft() {
        // flytter spilleren til venstre om mulig
        if (canMove(player.getRow(), player.getCol() - 1)) {
            player.moveLeft();
            checkGameOver(); // sjekker om vi har nådd mål
        }
    }

    public void moveRight() {
        // flytter spilleren til høyre om mulig
        if (canMove(player.getRow(), player.getCol() + 1)) {
            player.moveRight();
            checkGameOver(); // sjekker om vi har nådd mål
        }
    }

    public List<Highscore> getHighscores() throws IOException{
        // leser highscores fra highscores.txt og returnerer en sortert liste
        List<String> lines = LabyrinthFileHandler.readHighscores();
        List<Highscore> highscores = new ArrayList<>(); // liste som skal inneholde Highscore-objekter

        for (String line : lines) {
            // gjør hver linje i .txt-fila om til et Highscore-objekt og legger til lista
            String[] parts = line.split(","); // deler opp i navn og tid
            highscores.add(new Highscore(parts[0], Integer.parseInt(parts[1])));         
        }

        Collections.sort(highscores); // sorterer i stigende rekkefølge
        return highscores;
    }

    public void saveHighscore(String name) throws IOException {
        // lagrer en ny highscore til highscore.txt
        long time = getTimeUsed();
        LabyrinthFileHandler.writeHighscore(name, time);
    }
    
}

