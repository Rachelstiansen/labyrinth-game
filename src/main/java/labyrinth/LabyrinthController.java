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
    private int playerCol = 1;
    private int playerRow = 0;
    private Rectangle player;

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

        // labyrinthGrid.setPrefWidth(CELL_SIZE * labyrinth.get(0).length());
        // labyrinthGrid.setPrefHeight(CELL_SIZE * labyrinth.size());
        
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
        player = new Rectangle(CELL_SIZE, CELL_SIZE);
        player.setFill(Color.LIGHTGREEN);
        labyrinthGrid.add(player, playerCol, playerRow);
    }

    private void movePlayer(int rowChange, int colChange) {
        System.out.println("Moving player");
        
        int newRow = playerRow + rowChange;
        int newCol = playerCol + colChange;

        List<String> labyrinth = labyrinthGame.getLabyrinth();

        char square = labyrinth.get(newRow).charAt(newCol);

        if (square != '#') {
            GridPane.setRowIndex(player, newRow);
            GridPane.setColumnIndex(player, newCol);

            playerRow = newRow;
            playerCol = newCol;
        }
    }

    @FXML
    public void initialize() {
        
        labyrinthGrid.setFocusTraversable(true);

        labyrinthGrid.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            
            if (event.getCode() == KeyCode.UP) {
                movePlayer(-1, 0);
            }
            else if (event.getCode() == KeyCode.DOWN) {
                movePlayer(1, 0);
            }
            else if (event.getCode() == KeyCode.LEFT) {
                movePlayer(0, -1);
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                movePlayer(0, 1);
            }
        });
    }
       
}
