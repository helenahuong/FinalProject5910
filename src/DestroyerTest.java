import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DestroyerTest {
    private Destroyer destroyer;
    private Ocean ocean;

    @BeforeEach
    void setUp() {
        destroyer = new Destroyer();
        ocean = new Ocean();
    }

    @Test
    void getShipType() {
        assertEquals("Destroyer", destroyer.getShipType(), "getShipType should return 'Destroyer'.");
    }

    @Test
    void okToPlaceShipAt() {
        // Test for valid placement
        assertTrue(destroyer.okToPlaceShipAt(0, 0, true, ocean), "Should return true for valid placement horizontally.");
        assertTrue(destroyer.okToPlaceShipAt(0, 0, false, ocean), "Should return true for valid placement vertically.");

        // Test for invalid placement outside the ocean
        assertFalse(destroyer.okToPlaceShipAt(0, 9, true, ocean), "Should return false for invalid horizontal placement at edge of ocean.");
        assertFalse(destroyer.okToPlaceShipAt(9, 0, false, ocean), "Should return false for invalid vertical placement at edge of ocean.");
    }
}
