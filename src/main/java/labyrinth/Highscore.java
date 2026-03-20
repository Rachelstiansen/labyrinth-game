package labyrinth;

public class Highscore implements Comparable<Highscore>{
    private String name;
    private int time;
    
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
