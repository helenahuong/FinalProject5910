import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BattleshipTest {
    private Battleship battleship;
    private Ocean ocean;

    @BeforeEach
    void setUp() {
        battleship = new Battleship();
        ocean = new Ocean();
    }

    @Test
    void getShipType() {
        assertEquals("Battleship", battleship.getShipType(), "getShipType should return 'Battleship'.");
    }

    @Test
    void okToPlaceShipAt() {
        Ocean ocean = new Ocean();
        assertTrue(battleship.okToPlaceShipAt(0, 0, true, ocean), "Should be able to place at (0, 0) horizontally.");
        assertTrue(battleship.okToPlaceShipAt(0, 0, false, ocean), "Should be able to place at (0, 0) vertically.");

        assertFalse(battleship.okToPlaceShipAt(0, 7, true, ocean), "Should not be able to place horizontally at (0, 7).");
        assertFalse(battleship.okToPlaceShipAt(7, 0, false, ocean), "Should not be able to place vertically at (7, 0).");
    }

    @Test
    void placeShipAt() {
        Ocean ocean = new Ocean();
        battleship.placeShipAt(0, 0, true, ocean);
        assertSame(battleship, ocean.getShipArray()[0][0], "Battleship should be at (0, 0).");
        assertSame(battleship, ocean.getShipArray()[0][1], "Battleship should be at (0, 1).");
        assertSame(battleship, ocean.getShipArray()[0][2], "Battleship should be at (0, 2).");
        assertSame(battleship, ocean.getShipArray()[0][3], "Battleship should be at (0, 3).");
        assertNotSame(battleship, ocean.getShipArray()[1][0], "Battleship should not be at (1, 0).");
    }

}
