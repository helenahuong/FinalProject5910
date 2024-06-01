public interface OceanInterface {

	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 * Larger ships must be placed before smaller ones to avoid cases
	 * where it may be impossible to place the larger ships.
	 */
	void placeAllShipsRandomly();

	/**
	 * Checks if the given coordinate is not empty; that is, if it does not
	 * contain an EmptySea reference.
	 *
	 * @param row the row (0 to 9) in which to check for a floating ship
	 * @param column the column (0 to 9) in which to check for a floating ship
	 * @return true if the given location contains a ship, and false otherwise
	 */
	boolean isOccupied(int row, int column);

	/**
	 * Fires a shot at the given coordinate. This will update the number of shots
	 * that have been fired (and potentially the number of hits, as well).
	 *
	 * @param row the row (0 to 9) in which to shoot
	 * @param column the column (0 to 9) in which to shoot
	 * @return true if the given location contains a ship that is still afloat,
	 *         false if it does not or if the ship has been sunk
	 */
	boolean shootAt(int row, int column);

	/**
	 * Returns the number of shots fired in this game.
	 *
	 * @return the number of shots fired
	 */
	int getShotsFired();

	/**
	 * Returns the number of hits recorded in this game.
	 *
	 * @return the number of hits
	 */
	int getHitCount();

	/**
	 * Returns the number of ships sunk in this game.
	 *
	 * @return the number of ships sunk
	 */
	int getShipsSunk();

	/**
	 * Checks if the game is over; that is, if all ships have been sunk.
	 *
	 * @return true if all ships have been sunk, otherwise false
	 */
	boolean isGameOver();

	/**
	 * Provides access to the grid of ships in this Ocean.
	 *
	 * @return the 10x10 array of ships
	 */
	Ship[][] getShipArray();

	/**
	 * Prints the ocean. Helps the user to see the current state of the game.
	 */
	void print();
}
