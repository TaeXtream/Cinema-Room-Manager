package cinema;

import java.util.Scanner;

public class Cinema {

    private static Theater theater;
    private static int purchased = 0;
    private static int currentIncome = 0;

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();

        theater = new Theater(rows, cols);

        System.out.println();
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int input = scanner.nextInt();
            System.out.println();

            switch (input) {
                case 0:
                    return;
                case 1:
                    theater.printTheater();
                    break;
                case 2:
                    buySeat();
                    break;
                case 3:
                    printStatistics();
                    break;
                default:
                    throw new RuntimeException(String.format("unknown menu input %d", input));
            }
        }
    }

    private static Seat selectSeat() throws NonExistingSeatException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a row number:");
        int row = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int col = scanner.nextInt();

        System.out.println();

        if (row < 1 || row > theater.getRows() || col < 1 || col > theater.getCols()) {
            throw new NonExistingSeatException();
        }
        return new Seat(row, col);
    }



    private static void buySeat() {
        try {
            Seat seat = selectSeat();
            theater.takeSeat(seat);
            int price = theater.getPrice(seat);
            currentIncome += price;
            purchased++;
            System.out.println();
            System.out.printf("Ticket price: $%s", price);
            System.out.println();
        } catch (NonExistingSeatException e) {
            System.out.println("Wrong input! ");
            System.out.println();
            buySeat();
        } catch (AlreadyTakenSeatException e) {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            buySeat();
        }
    }

    private static void printStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", purchased);
        System.out.printf("Percentage: %s%%%n", getPercentage());
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", getTotalIncome());
        System.out.println();
    }

    private static String getPercentage() {
        double percentage = (double) purchased * 100 / theater.totalSeats();
        return String.format("%.2f", percentage);
    }

    private static int getTotalIncome() {
        int rows = theater.getRows();
        int cols = theater.getCols();
        if (theater.totalSeats() <= 60) {
            return rows * cols * 10;
        } else {
            return (rows / 2) * cols * 10 + (rows - rows / 2) * cols * 8;
        }
    }



}

class NonExistingSeatException extends Exception {}
