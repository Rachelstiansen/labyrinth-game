package labyrinth;

public class Player implements Movable{
    // Skal holde styr på spillerens posisjon
    private int row;
    private int col;

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    @Override
    public void moveUp() {
        this.row--;
    }
    @Override
    public void moveDown() {
        this.row++;
    }
    @Override
    public void moveLeft() {
        this.col--;
    }
    @Override
    public void moveRight() {
        this.col++;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
