package cinema;

import java.util.Arrays;

public class Theater {
    private final int rows;
    private final int cols;
    private final String[][] seats;

    public Theater(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        seats = createEmptySeats(rows, cols);
    }

    private static String[][] createEmptySeats(int rows, int cols) {
        String[][] seats = new String[rows][];
        for (int i = 0; i < rows; i++) {
            String[] row = new String[cols];
            Arrays.fill(row, "S");
            seats[i] = row;
        }
        return seats;
    }

    private String getTopRow() {
        String[] topRow = new String[this.cols + 1];

        topRow[0] = " ";
        for (int i = 1; i < topRow.length; i++) {
            topRow[i] = String.valueOf(i);
        }
        return String.join(" ", topRow);
    }

    public void printTheater() {
        System.out.println();
        System.out.println("Cinema:");

        String topRow = getTopRow();
        System.out.println(topRow);

        for (int i = 1; i <= seats.length; i++) {
            System.out.printf("%d ", i);
            String row = String.join(" ", seats[i - 1]);
            System.out.println(row);
        }
    }

    public int totalSeats() {
        return cols * rows;
    }

    public void takeSeat(Seat seat) throws AlreadyTakenSeatException {
        if (seats[seat.getRow() - 1][seat.getCol() - 1].equals("B")) {
            throw new AlreadyTakenSeatException();
        } else {
            seats[seat.getRow() - 1][seat.getCol() - 1] = "B";
        }
    }

    private boolean isFrontHalf(Seat seat) {
        return seat.getRow() <= rows / 2;
    }

    public int getPrice(Seat seat) {
        if (totalSeats() <= 60 || isFrontHalf(seat)) {
            return 10;
        }
        return 8;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}

class AlreadyTakenSeatException extends Exception {}

