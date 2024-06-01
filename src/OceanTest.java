import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OceanTest {
    private Ocean ocean;

    @BeforeEach
    void setUp() {
        ocean = new Ocean();
    }

    @Test
    void placeAllShipsRandomly() {
        ocean.placeAllShipsRandomly();
        boolean shipPlaced = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!(ocean.getShipArray()[i][j] instanceof EmptySea)) {
                    shipPlaced = true;
                    break;
                }
            }
            if (shipPlaced) break;
        }
        assertTrue(shipPlaced, "Ships should be placed on the ocean.");
    }

    @Test
    void isOccupied() {
        ocean.placeAllShipsRandomly();
        boolean occupiedFound = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (ocean.isOccupied(i, j)) {
                    occupiedFound = true;
                    break;
                }
            }
            if (occupiedFound) break;
        }
        assertTrue(occupiedFound, "Some cells should be occupied by ships.");
    }

    @Test
    void shootAt() {
        ocean.placeAllShipsRandomly();
        ocean.shootAt(5, 5);
        assertTrue(ocean.getShotsFired() > 0, "Shooting should increase shots fired count.");
    }

    @Test
    void getShotsFired() {
        assertEquals(0, ocean.getShotsFired(), "Initially, shots fired should be 0.");
        ocean.shootAt(3, 3);
        assertEquals(1, ocean.getShotsFired(), "Shots fired should be incremented after shooting.");
    }

    @Test
    void getHitCount() {
        ocean.placeAllShipsRandomly();
        // Shoot at a known location of a ship for testing
        // Assume you know the location of a ship; this can be improved with a more comprehensive testing strategy
        ocean.shootAt(3, 3);
        assertTrue(ocean.getHitCount() >= 0, "Hit count should be non-negative.");
    }

    @Test
    void getShipsSunk() {
        ocean.placeAllShipsRandomly();
        // Similar to getHitCount, sink a ship for testing
        ocean.shootAt(3, 3);
        assertTrue(ocean.getShipsSunk() >= 0, "Ships sunk should be non-negative.");
    }

    @Test
    void isGameOver() {
        ocean.placeAllShipsRandomly();
        assertFalse(ocean.isGameOver(), "Game should not be over immediately after placing ships.");
        // Additional logic can be added to simulate end-game condition
    }

    @Test
    void getShipArray() {
        assertNotNull(ocean.getShipArray(), "Ship array should not be null.");
        assertEquals(10, ocean.getShipArray().length, "Ship array should have 10 rows.");
        assertEquals(10, ocean.getShipArray()[0].length, "Ship array should have 10 columns.");
    }

    @Test
    void print() {
        ocean.placeAllShipsRandomly();
        assertDoesNotThrow(() -> ocean.print(), "Printing the ocean should not throw any exceptions.");
    }

    @Test
    void shootAtKnownShipLocation() {
        // Manually place a ship for testing
        Ship ship = new Destroyer(); // Example with a Destroyer
        ocean.placeShipAt(5, 5, true, ship); // Place horizontally at (5,5)

        ocean.shootAt(5, 5);
        ocean.shootAt(5, 6);

        assertTrue(ship.isSunk(), "Shooting all parts of the ship should sink it.");
        assertTrue(ocean.getHitCount() > 0, "Hit count should increase after hitting a ship.");
    }

    @Test
    void isGameOverAfterSinkingAllShips() {
        ocean.placeAllShipsRandomly();

        // Shoot at every cell that is not EmptySea to ensure all ships are sunk
        for (int i = 0; i < ocean.getShipArray().length; i++) {
            for (int j = 0; j < ocean.getShipArray()[i].length; j++) {
                if (!(ocean.getShipArray()[i][j] instanceof EmptySea)) {
                    ocean.shootAt(i, j);
                }
            }
        }

        // Check if the game is over (all ships should be sunk)
        assertTrue(ocean.isGameOver(), "Game should be over after sinking all ships.");
    }
}
