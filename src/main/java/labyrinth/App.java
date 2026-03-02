package labyrinth;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        Button button = new Button("Test JavaFX");
        Scene scene = new Scene(button, 400, 300);

        stage.setScene(scene);
        stage.setTitle("JavaFX Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
