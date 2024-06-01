import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();

        while (!ocean.isGameOver()) {
            System.out.println("Current Ocean:");
            ocean.print();

            // Get user input for row and column
            System.out.print("Enter row (0-9): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-9): ");
            int column = scanner.nextInt();

            // Get ship type before shooting
            String shipTypeBefore = ocean.getShipTypeAt(row, column);
            boolean wasSunkBefore = ocean.getShipArray()[row][column].isSunk();

            // Shoot at the given location
            boolean hit = ocean.shootAt(row, column);
            if (hit) {
                System.out.println("You hit a ship!");
                // Check if the ship is sunk after the shot
                if (!wasSunkBefore && ocean.getShipArray()[row][column].isSunk()) {
                    System.out.println("You just sunk a " + shipTypeBefore + "!");
                }
            } else {
                System.out.println("Miss.");
            }

            // Display current game status
            System.out.println("Shots fired: " + ocean.getShotsFired());
            System.out.println("Hits: " + ocean.getHitCount());
            System.out.println("Ships sunk: " + ocean.getShipsSunk());
            System.out.println();
        }

        System.out.println("Game over! You sunk all the ships.");
        scanner.close();
    }
}
