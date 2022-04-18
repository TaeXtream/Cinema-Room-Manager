package cinema;

public class Seat {
    private final int row;
    private final int col;

    Seat(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
