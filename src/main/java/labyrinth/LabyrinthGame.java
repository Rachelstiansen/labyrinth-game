package labyrinth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LabyrinthGame {

    private List<String> labyrinth;
    private Player player;
    private boolean gameOver;
    private long startTime;
    private long endTime;
    

    public LabyrinthGame() {
        this.gameOver = false;
    }

    public void startTimer() {
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
        // Set starting position
        player = new Player(row, col);
    }

    public Player getPlayer() {
        return player;
    }

    public char getCurrentTile() {
        return labyrinth.get(player.getRow()).charAt(player.getCol());
    }

    public void checkGameOver() {
        if (getCurrentTile() == 'F') {
            gameOver = true;
            endTime = System.currentTimeMillis();
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
        if (row < 0 || row >= labyrinth.size()) {
            return false;
        }
        if (col < 0 || col >= labyrinth.get(0).length()) {
            return false;
        }
        return labyrinth.get(row).charAt(col) != '#';
    }

    public void moveUp() {
        if (canMove(player.getRow() - 1, player.getCol())) {
            player.moveUp();
            checkGameOver();
        }
    }
    
    public void moveDown() {
        if (canMove(player.getRow() + 1, player.getCol())) {
            player.moveDown();
            checkGameOver();
        }
    }

    public void moveLeft() {
        if (canMove(player.getRow(), player.getCol() - 1)) {
            player.moveLeft();
            checkGameOver();
        }
    }

    public void moveRight() {
        if (canMove(player.getRow(), player.getCol() + 1)) {
            player.moveRight();
            checkGameOver();
        }
    }

    public List<Highscore> getHighscores() throws IOException{
        
        List<String> lines = LabyrinthFileHandler.readHighscores();
        List<Highscore> highscores = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            highscores.add(new Highscore(parts[0], Integer.parseInt(parts[1])));         
        }

        Collections.sort(highscores);
        return highscores;
    }

    public void saveHighscore(String name) throws IOException {
        long time = getTimeUsed();
        LabyrinthFileHandler.writeHighscore(name, time);
    }
    
}

