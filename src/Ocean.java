import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class Ocean implements OceanInterface {

	// Existing member variables
	protected Ship[][] ships;
	protected int shotsFired;
	protected int hitCount;
	protected int shipsSunk;
	private List<Ship> fleet;
	private final int totalNumberOfShips = 10;

	// Constructor
	public Ocean() {
		fleet = new ArrayList<>();

		// Add 1 Battleship
		fleet.add(new Battleship());

		// Add 2 Cruisers
		fleet.add(new Cruiser());
		fleet.add(new Cruiser());

		// Add 3 Destroyers
		fleet.add(new Destroyer());
		fleet.add(new Destroyer());
		fleet.add(new Destroyer());

		// Add 4 Submarines
		fleet.add(new Submarine());
		fleet.add(new Submarine());
		fleet.add(new Submarine());
		fleet.add(new Submarine());

		// Initialize the rest of the Ocean grid with EmptySea instances
		ships = new Ship[10][10];
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea();
			}
		}
	}

	// Place all ships randomly
	public void placeAllShipsRandomly() {
		Random random = new Random();

		for (Ship ship : fleet) {
			boolean placed = false;
			while (!placed) {
				int row = random.nextInt(ships.length);
				int column = random.nextInt(ships[0].length);
				boolean horizontal = random.nextBoolean();

				// Check if it's okay to place the ship at the generated position
				if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
					ship.placeShipAt(row, column, horizontal, this);
					placed = true;
				}
			}
		}
	}


	public void placeShipAt(int row, int column, boolean horizontal, Ship ship) {
		ship.placeShipAt(row, column, horizontal, this);
	}
	// Check if given coordinate contains a ship

	public String getShipTypeAt(int row, int column) {
		return ships[row][column].getShipType();
	}

	@Override
	public boolean isOccupied(int row, int column) {
		return !(ships[row][column] instanceof EmptySea);
	}

	// Fire a shot at the given coordinate
	@Override
	public boolean shootAt(int row, int column) {
		this.shotsFired++;
		Ship targetShip = this.ships[row][column];
		boolean wasSunk = targetShip.isSunk();
		boolean shotResult = targetShip.shootAt(row, column);
		if (shotResult) {
			if (!(targetShip instanceof EmptySea)) {
				this.hitCount++;
			}
			if (!wasSunk && targetShip.isSunk()) {
				this.shipsSunk++;
			}
			return true;
		}
		return false;
	}


	// Getters for game state
	@Override
	public int getShotsFired() {
		return shotsFired;
	}

	@Override
	public int getHitCount() {
		return hitCount;
	}

	@Override
	public int getShipsSunk() {
		return shipsSunk;
	}
	public boolean isGameOver() {
		// Check if the number of sunk ships equals the total number of ships
		int sunkShipsCount = 0;
		for (Ship ship : fleet) {
			if (ship.isSunk()) {
				sunkShipsCount++;
			}
		}

		return sunkShipsCount == fleet.size();
	}


	@Override
	public Ship[][] getShipArray() {
		return ships;
	}

	// Print the ocean
	@Override
	public void print() {
		// Column numbers
		System.out.print(" ");
		for (int i = 0; i < ships[0].length; i++) {
			System.out.print(" " + i);
		}
		System.out.println();

		for (int row = 0; row < ships.length; row++) {
			// Row number
			System.out.print(row);

			for (int column = 0; column < ships[row].length; column++) {
				if (ships[row][column].isHorizontal()) {
					// Ship is horizontal
					System.out.print(" " + ships[row][column].toString());
				} else {
					// Ship is vertical or EmptySea
					System.out.print(" " + ships[row][column].toString());
				}
			}
			System.out.println();
		}
	}
}
