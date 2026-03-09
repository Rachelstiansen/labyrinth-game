package labyrinth;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LabyrinthController {

    @FXML
    private GridPane labyrinthGrid; // kobler til GridPane i fxml

    private static final int CELL_SIZE = 25; // str på hver rute i labyrinten

    private LabyrinthGame labyrinthGame;
    private Player player;
    private Rectangle playerNode;

    @FXML
    public void startGame() {
        // System.out.println("Knappen fungerer!");
        labyrinthGame = new LabyrinthGame(); // oppretter LabyrinthGame-objekt

        try {
            // leser labyrinten fra .txt-fil med Files.readAllLines()
            labyrinthGame.loadMaze("src/main/resources/labyrinth/labyrinth.txt");
            // Hver linje i lista blir en rad i labyrinten
        }
        catch (IOException e) {
            // Hvis filen ikke finnes, skrives ut feilmelding
            e.printStackTrace();
            System.out.println("Unable to load labyrinth: " + e.getMessage());
            return;
        }
        drawLabyrinth(); // Fyller GridPane med ruter som representerer vegger og sti
        drawPlayer();
        
        labyrinthGrid.requestFocus();
    }

    private void drawLabyrinth() {
        
        List<String> labyrinth = labyrinthGame.getLabyrinth();
        // Henter ut listen hvor hver streng er en rad i labyrinten

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
        playerNode = new Rectangle(CELL_SIZE, CELL_SIZE);
        playerNode.setFill(Color.LIGHTGREEN);
        player = new Player(0, 1);
        labyrinthGrid.add(playerNode, player.getCol(), player.getRow());
    }

    private boolean canMove(int row, int col) {
        // Checks if the player can move to the next square
        List<String> labyrinth = labyrinthGame.getLabyrinth();
        return labyrinth.get(row).charAt(col) != '#';
    }

    @FXML
    public void initialize() {
        labyrinthGrid.setFocusTraversable(true);

        labyrinthGrid.setOnKeyPressed(event -> {
            int row = player.getRow();
            int col = player.getCol();

            if (event.getCode() == KeyCode.UP && canMove(row - 1, col)) {
                player.moveUp();
            }
            else if (event.getCode() == KeyCode.DOWN && canMove(row + 1, col)) {
                player.moveDown();
            }
            else if (event.getCode() == KeyCode.LEFT && canMove(row, col - 1)) {
                player.moveLeft();
            }
            else if (event.getCode() == KeyCode.RIGHT && canMove(row, col + 1)) {
                player.moveRight();
            }

            GridPane.setRowIndex(playerNode, player.getRow());
            GridPane.setColumnIndex(playerNode, player.getCol());
        });
    }
 
}
