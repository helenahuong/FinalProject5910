import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CruiserTest {
    private Cruiser cruiser;
    private Ocean ocean;

    @BeforeEach
    void setUp() {
        cruiser = new Cruiser();
        ocean = new Ocean();
    }

    @Test
    void getShipType() {
        assertEquals("Cruiser", cruiser.getShipType(), "getShipType should return 'Cruiser'.");
    }

    @Test
    void okToPlaceShipAt() {
        // Placing the cruiser within the ocean bounds and without overlapping any ships
        assertTrue(cruiser.okToPlaceShipAt(0, 0, true, ocean), "Should return true for valid placement.");

        // Placing the cruiser at a position where it would extend outside the ocean grid
        assertFalse(cruiser.okToPlaceShipAt(ocean.getShipArray().length - 1, ocean.getShipArray()[0].length - 1, true, ocean), "Should return false for invalid placement.");

        // Additional tests should be added to check for overlapping with other ships
    }

    @Test
    void placeShipAt() {
        cruiser.placeShipAt(0, 0, true, ocean);

        // Verifying the cruiser is placed correctly
        for (int i = 0; i < cruiser.getLength(); i++) {
            assertSame(cruiser, ocean.getShipArray()[0][i], "Cruiser should be placed horizontally starting at (0, 0).");
        }

        // Ensure the parts of the ocean grid not occupied by the cruiser remain EmptySea
        assertTrue(ocean.getShipArray()[1][0] instanceof EmptySea, "This part of the ocean should still be empty.");
    }
}
