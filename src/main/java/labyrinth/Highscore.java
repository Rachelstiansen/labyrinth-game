package labyrinth;

// Representerer en highscore-entry i labyrint-spillet
public class Highscore implements Comparable<Highscore>{
    
    private String name;
    private int time; // tid i sekunder spilleren brukte på å komme gjennom labyrinten
    
    public Highscore(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public int compareTo(Highscore other) {
        // Sorterer highscore basert på tid i stigende rekkefølge
        return Integer.compare(this.time, other.time);
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}
