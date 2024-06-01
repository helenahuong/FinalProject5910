import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    Ship ship;

    @BeforeEach
    void setUp() {
        // Use an instance of a concrete subclass for testing
        ship = new Battleship();
    }

    @Test
    void getBowRow() {
        ship.setBowRow(3);
        assertEquals(3, ship.getBowRow());
    }

    @Test
    void setBowRow() {
        ship.setBowRow(4);
        assertEquals(4, ship.getBowRow());
    }

    @Test
    void getBowColumn() {
        ship.setBowColumn(2);
        assertEquals(2, ship.getBowColumn());
    }

    @Test
    void setBowColumn() {
        ship.setBowColumn(5);
        assertEquals(5, ship.getBowColumn());
    }

    @Test
    void getLength() {
        // Assuming Battleship's length is 4
        assertEquals(4, ship.getLength());
    }

    @Test
    void isHorizontal() {
        ship.setHorizontal(true);
        assertTrue(ship.isHorizontal());

        ship.setHorizontal(false);
        assertFalse(ship.isHorizontal());
    }

    @Test
    void setHorizontal() {
        ship.setHorizontal(true);
        assertTrue(ship.isHorizontal());
    }

    @Test
    void getShipType() {
        assertEquals("Battleship", ship.getShipType());
    }

    @Test
    void okToPlaceShipAt() {
        Ocean ocean = new Ocean();
        assertTrue(ship.okToPlaceShipAt(0, 0, true, ocean));
        // More tests can be added for various scenarios
    }

    @Test
    void placeShipAt() {
        Ocean ocean = new Ocean();
        ship.placeShipAt(0, 0, true, ocean);
        // Check if the ship is placed correctly
        assertEquals(ship, ocean.getShipArray()[0][0]);
        assertEquals(ship, ocean.getShipArray()[0][1]); // Assuming ship length > 1
        // More checks can be added based on ship length and orientation
    }

    @Test
    void shootAt() {
        ship.placeShipAt(0, 0, true, new Ocean());
        assertFalse(ship.isSunk());
        ship.shootAt(0, 0);
        ship.shootAt(0, 1);
        ship.shootAt(0, 2);
        ship.shootAt(0, 3);
        assertTrue(ship.isSunk());
    }

    @Test
    void isSunk() {
        ship.placeShipAt(0, 0, true, new Ocean());
        ship.shootAt(0, 0);
        ship.shootAt(0, 1);
        ship.shootAt(0, 2);
        assertFalse(ship.isSunk());
        ship.shootAt(0, 3); // Assuming the length of the ship is 4
        assertTrue(ship.isSunk());
    }

    @Test
    void testToString() {
        ship.placeShipAt(0, 0, true, new Ocean());
        ship.shootAt(0, 0);
        assertEquals("S", ship.toString());
        ship.shootAt(0, 1);
        ship.shootAt(0, 2);
        ship.shootAt(0, 3);
        assertEquals("x", ship.toString());
    }
}
