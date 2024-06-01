import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubmarineTest {

    private Submarine submarine;
    private Ocean ocean;

    @BeforeEach
    void setUp() {
        submarine = new Submarine();
        ocean = new Ocean();
    }

    @Test
    void okToPlaceShipAt() {
        assertTrue(submarine.okToPlaceShipAt(5, 5, true, ocean), "Should return true for valid placement.");

        assertFalse(submarine.okToPlaceShipAt(10, 10, true, ocean), "Should return false for placement outside ocean bounds.");
    }

    @Test
    void placeShipAt() {
        submarine.placeShipAt(3, 3, true, ocean);
        assertSame(submarine, ocean.getShipArray()[3][3], "Submarine should be placed at (3, 3).");

        assertTrue(ocean.getShipArray()[3][4] instanceof EmptySea, "Adjacent location (3, 4) should be EmptySea.");
    }
}
