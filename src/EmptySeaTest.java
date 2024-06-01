import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmptySeaTest {
    private EmptySea emptySea;
    private Ocean ocean;

    @BeforeEach
    void setUp() {
        emptySea = new EmptySea();
        ocean = new Ocean();
    }

    @Test
    void getShipType() {
        assertEquals("empty", emptySea.getShipType(), "getShipType should return 'empty' for EmptySea.");
    }

    @Test
    void shootAt() {
        assertFalse(emptySea.shootAt(0, 0), "Shooting at EmptySea should always return false.");
    }

    @Test
    void isSunk() {
        assertFalse(emptySea.isSunk(), "EmptySea should always return false for isSunk.");
    }

    @Test
    void testToString() {
        assertEquals("-", emptySea.toString(), "EmptySea should return '-' for toString method.");
    }

    @Test
    void okToPlaceShipAt() {
        assertFalse(emptySea.okToPlaceShipAt(0, 0, true, ocean), "okToPlaceShipAt should always return false for EmptySea.");
    }

    @Test
    void placeShipAt() {
        emptySea.placeShipAt(0, 0, true, ocean);
        // Since EmptySea doesn't really get 'placed', the ocean at (0, 0) should still be EmptySea
        assertTrue(ocean.getShipArray()[0][0] instanceof EmptySea, "The ocean at (0, 0) should still be EmptySea after placing EmptySea.");
    }
}
