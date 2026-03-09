package labyrinth;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LabyrinthController {
    private static final int CELL_SIZE = 30;

    @FXML
    private GridPane labyrinthGrid;

    private LabyrinthGame labyrinthGame;

    @FXML
    public void startGame() {
        System.out.println("Knappen fungerer!");
        labyrinthGame = new LabyrinthGame();

        try {
            labyrinthGame.loadMaze("src/main/resources/labyrinth/labyrinth.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to load labyrinth: " + e.getMessage());
            return;
        }
        drawLabyrinth();
    }

    private void drawLabyrinth() {
        
        List<String> labyrinth = labyrinthGame.getLabyrinth();
        // labyrinthGrid.getChildren().clear();

        labyrinthGrid.setPrefWidth(CELL_SIZE * labyrinth.get(0).length());
        labyrinthGrid.setPrefHeight(CELL_SIZE * labyrinth.size());
        
        for (int row = 0; row < labyrinth.size(); row++) {
            String line = labyrinth.get(row);

            for (int col = 0; col < line.length(); col++) {

                char square = line.charAt(col);
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                
                if (square == '#') {
                    cell.setFill(Color.DARKCYAN);
                }
                else {
                    cell.setFill(Color.ANTIQUEWHITE);
                }

                labyrinthGrid.add(cell, col, row);
            }
        }
    }
       
}
