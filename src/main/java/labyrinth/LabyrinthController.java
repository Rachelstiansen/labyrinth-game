package labyrinth;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Kontrollerklasse som håndterer brukergresesnittet, kobler sammen LabyrinthGame
// med det som brukeren ser på skjermen.
public class LabyrinthController {

    // kobler til elementer i fxml-fila:
    @FXML private GridPane labyrinthGrid;
    @FXML private Button startButton;
    @FXML private Button resetButton;
    @FXML private ListView<String> highscoreList;

    private static final int CELL_SIZE = 25; // str på hver rute i labyrinten i piksler

    private LabyrinthGame labyrinthGame; // lagrer én konkret instans av labyrintspillet
    private Rectangle playerNode;        // grafisk representasjon av spilleren
    private boolean scoreSaved = false;  // hindrer at scoren lagres flere ganger når spillet er ferdig

    @FXML
    public void startGame() {
        // Kjøres når start-knappen trykkes
        labyrinthGame = new LabyrinthGame(); // oppretter spill/model-objektet
        startButton.setVisible(false); // fjerner startknappen når spillet har startet
        
        try {
            // leser labyrinten fra .txt-fil med Files.readAllLines()
            labyrinthGame.loadLabyrinth("src/main/resources/labyrinth/labyrinth.txt");
            // Hver streng i lista blir en rad i labyrinten
        }
        catch (IOException e) {
            // Hvis filen ikke finnes eller ikke kan leses, skrives ut feilmelding
            System.out.println("Unable to load labyrinth: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        labyrinthGame.createPlayer(0, 1); // lager spiller i startposisjonen
        drawLabyrinth();            // Fyller GridPane med ruter som representerer vegger og sti
        drawPlayer();               // Tegner spiller-blokk
        initializeControls();       // Aktiverer tastaturkontroller så spillet responderer på keyboard-input
        labyrinthGame.startTimer(); // starter tidtakingen
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
                    cell.setFill(Color.DARKCYAN); // farge på vegg
                }
                else {
                    cell.setFill(Color.ANTIQUEWHITE); // farge på sti
                }
                labyrinthGrid.add(cell, col, row); // Legger til ruta i GridPane
            }
        }
    }

    private void drawPlayer() {
        // tegner spilleren i startposisjonen
        playerNode = new Rectangle(CELL_SIZE, CELL_SIZE); // Lager grafisk firkant til spiller-brikken
        playerNode.setFill(Color.LIGHTGREEN);             // Farger brikken
        Player player = labyrinthGame.getPlayer();        // Henter spilleren
        labyrinthGrid.add(playerNode, player.getCol(), player.getRow()); // Setter inn spilleren i GridPane
    }

    private void updatePlayerPosition() {
        // Metoden kalles i initializeControls for å oppdatere posisjonen til spilleren
        // når brukeren trykker på piltastene på tastaturet.
        Player player = labyrinthGame.getPlayer();
        // Flytter spilleren til ny rad og kolonne:
        GridPane.setRowIndex(playerNode, player.getRow());
        GridPane.setColumnIndex(playerNode, player.getCol());
    }

    public void initializeControls() {
        // gridPane må ha fokus for å kunne motta input fra tastaturet, ellers vil 
        // ikke spilleren kunne flytte på seg.
        labyrinthGrid.setFocusTraversable(true);

        labyrinthGrid.setOnKeyPressed(event -> {
            // sjekker at spillet er initialisert
            if (labyrinthGame == null || labyrinthGame.getPlayer() == null) {
                return;
            }
            // Flytter spiller basert på input fra piltastene
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
            updatePlayerPosition(); // oppdaterer spillerposisjonen i gridet (visuelt)
            
            // sjekker om spillet er ferdig
            if (labyrinthGame.isGameOver() && !scoreSaved) {
                scoreSaved = true;
                handleGameOver();
            }
        });
    }

    private String handleNameInput() {
        // Lager en dialog-box for å ta inn navnet til spilleren
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Highscore");
        dialog.setHeaderText("You finished!");
        dialog.setContentText("Enter your name: ");       
        String name = dialog.showAndWait().orElse("Anonymous"); // Viser dialog-boks til brukeren
        // og venter på at OK trykkes. Hvis ingenting skrives returneres "Anonymous" som spillernavn. 
        return name;
    }

    public void loadHighscores() {
        // Laster og viser highscores i ListView
        try {
            List<Highscore> highscores = labyrinthGame.getHighscores();
            highscoreList.getItems().clear(); // Tømmer listen før nye verdier legges til

            for (Highscore highscore : highscores) {
                highscoreList.getItems().add(highscore.getName() + " - " + highscore.getTime() + " s");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleGameOver() {
        // håndterer hva som skjer når spilleren har kommet gjennom labyrinten
        String name = handleNameInput();
        try {
            // lagrer highscore i .txt-fila
            labyrinthGame.saveHighscore(name);

        } catch (IOException e) {
            e.printStackTrace();
        }
        loadHighscores(); // oppdaterer highscore-listen
    }

    @FXML
    private void handleResetHighscores() {
        // Kjøres når reset-knappen trykkes
        try {
            LabyrinthFileHandler.clearHighscores(); // sletter alle highscores
            loadHighscores(); // oppdaterer highscore-listen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}
