package labyrinth;

// Skal holde styr på spillerens posisjon
public class Player implements Movable{
    private int row; // nåværende rad til spilleren
    private int col; // nåværende kolonne til spilleren

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    @Override
    public void moveUp() {
        // Flytter spilleren en rad oppover
        this.row--;
    }
    @Override
    public void moveDown() {
        // Flytter spilleren en rad nedover
        this.row++;
    }
    @Override
    public void moveLeft() {
        // Flytter spilleren en kolonne til venstre
        this.col--;
    }
    @Override
    public void moveRight() {
        // Flytter spilleren en kolonne til høyre
        this.col++;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
