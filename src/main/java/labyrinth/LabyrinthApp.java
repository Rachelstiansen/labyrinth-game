package labyrinth;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LabyrinthApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Labyrinth-game");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("game.fxml"))));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

}
