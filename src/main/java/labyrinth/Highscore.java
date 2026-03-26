package labyrinth;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Highscore implements Comparable<Highscore>{
    @FXML private ListView<String> highscoreList;
    
    private String name;
    private int time;
    private LabyrinthGame labyrinthGame;
    
    public Highscore(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public int compareTo(Highscore other) {
        return Integer.compare(this.time, other.time);
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}
