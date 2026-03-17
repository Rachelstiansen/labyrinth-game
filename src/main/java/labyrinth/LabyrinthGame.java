package labyrinth;

import java.io.IOException;
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

    private void startTimer() {
        startTime = System.currentTimeMillis();
    }

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

    public char getCurrentTile() {
        return labyrinth.get(player.getRow()).charAt(player.getCol());
    }

    public void checkGameOver() {
        if (getCurrentTile() == 'F') {
            gameOver = true;
        }
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
}

