package labyrinth;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LabyrinthController {

    // kobler til GridPane i fxml-fila:
    @FXML private GridPane labyrinthGrid;
    @FXML private Button startButton;
    @FXML private ListView<String> highscoreList;

    private static final int CELL_SIZE = 25; // str på hver rute i labyrinten i piksler

    private LabyrinthGame labyrinthGame;
    private Rectangle playerNode;
    private long elapsedTime;
    private boolean scoreSaved;

    @FXML
    public void startGame() {
        // Kjøres når start-knappen trykkes
        labyrinthGame = new LabyrinthGame(); // oppretter spill/model-objektet
        startButton.setVisible(false); // fjerner startknappen for spillet har allerede startet
        
        try {
            // leser labyrinten fra .txt-fil med Files.readAllLines()
            labyrinthGame.loadMaze("src/main/resources/labyrinth/labyrinth.txt");
            // Hver streng i lista blir en rad i labyrinten
        }
        catch (IOException e) {
            // Hvis filen ikke finnes eller ikke kan leses, skrives ut feilmelding
            e.printStackTrace();
            System.out.println("Unable to load labyrinth: " + e.getMessage());
            return;
        }
        labyrinthGame.createPlayer(0, 1); // lager spiller i startposisjonen

        drawLabyrinth();    // Fyller GridPane med ruter som representerer vegger og sti
        drawPlayer();       // Tegner spiller-blokk
        setupKeyHandler();  // Aktiverer keyHandler så spillet responderer på keyboard-input
        labyrinthGame.startTimer();
    }

    private void drawLabyrinth() {
        
        // Henter ut listen hvor hver streng er en rad i labyrinten
        List<String> labyrinth = labyrinthGame.getLabyrinth();

        for (int row = 0; row < labyrinth.size(); row++) {
            // itererer gjennom alle radene
            String line = labyrinth.get(row); // henter ut raden

            for (int col = 0; col < line.length(); col++) {
                // og alle kolonnene
                char square = line.charAt(col); // henter ut tegnet i ruta
                // Lager grafisk firkant som settes inn i GridPane:
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE); 
                
                // Setter fargen på vegg og sti
                if (square == '#') {
                    cell.setFill(Color.DARKCYAN);
                }
                else {
                    cell.setFill(Color.ANTIQUEWHITE);
                }
                labyrinthGrid.add(cell, col, row); // Legger til ruta i GridPane
            }
        }
    }

    private void drawPlayer() {
        playerNode = new Rectangle(CELL_SIZE, CELL_SIZE); // Lager grafisk firkant til spiller-brikken
        playerNode.setFill(Color.LIGHTGREEN); 
        Player player = labyrinthGame.getPlayer();
        labyrinthGrid.add(playerNode, player.getCol(), player.getRow()); // Setter inn spilleren i GridPane
    }

    private void updatePlayerPosition() {
        // Metoden kalles i setupKeyHandler for å oppdatere posisjonen til spilleren
        // når brukeren trykker på piltastene på tastaturet.
        Player player = labyrinthGame.getPlayer();
        GridPane.setRowIndex(playerNode, player.getRow());
        GridPane.setColumnIndex(playerNode, player.getCol());
    }

    public void setupKeyHandler() {
        // gridPane må ha fokus for å kunne motta input fra tastaturet, ellers vil 
        // ikke spilleren kunne flytte på seg.
        labyrinthGrid.setFocusTraversable(true);

        labyrinthGrid.setOnKeyPressed(event -> {
            if (labyrinthGame == null || labyrinthGame.getPlayer() == null) {
                return;
            }

            if (event.getCode() == KeyCode.UP) {
                labyrinthGame.moveUp();
            }
            else if (event.getCode() == KeyCode.DOWN) {
                labyrinthGame.moveDown();
            }
            else if (event.getCode() == KeyCode.LEFT) {
                labyrinthGame.moveLeft();
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                labyrinthGame.moveRight();
            }

            updatePlayerPosition();
            
            if (labyrinthGame.isGameOver() && !scoreSaved) {
                scoreSaved = true;
                handleGameOver();
                System.out.println("You reached finish line in " + labyrinthGame.getTimeUsed() + " seconds!");
            }

        });
    }

    private void handleGameOver() {
        long time = labyrinthGame.getTimeUsed();

        try {
            LabyrinthFileHandler.writeHighscore("Player", time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadHighscores();
    }

    private void loadHighscores() {
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
 
}
