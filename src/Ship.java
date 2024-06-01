public abstract class Ship {
    protected int bowRow;
    protected int bowColumn;
    protected int length;
    protected boolean horizontal;
    protected boolean[] hit;

    // Constructor
    public Ship() {
        // Initialize the hit array with default size. This will be reinitialized in subclasses.
        this.hit = new boolean[4]; // Default size, to be overridden in subclasses
    }

    // Getters and Setters
    public int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    // Abstract methods
    public abstract String getShipType();

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Check ocean bounds
        if (horizontal && column + this.length > ocean.getShipArray()[0].length ||
                !horizontal && row + this.length > ocean.getShipArray().length) {
            return false; // Out of bounds
        }

        // Check for overlaps and neighbor constraints
        for (int i = 0; i < this.length; i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? column + i : column;

            if (!isPositionValid(r, c, ocean)) {
                return false;
            }
        }

        return true;
    }

    // Place the ship in the ocean
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.bowRow = row;
        this.bowColumn = column;
        this.horizontal = horizontal;

        // Update the ocean grid with references to this ship
        for (int i = 0; i < this.length; i++) {
            if (horizontal) {
                ocean.getShipArray()[row][column + i] = this;
            } else {
                ocean.getShipArray()[row + i][column] = this;
            }
        }
    }

    private boolean isPositionValid(int row, int column, Ocean ocean) {
        // Check if the position is within bounds and if it's an EmptySea
        if (row < 0 || row >= ocean.getShipArray().length || column < 0 || column >= ocean.getShipArray()[0].length) {
            return false;
        }

        // Check for the neighbor constraints
        // Ensure no adjacent ships in the surrounding cells
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int newRow = row + dr;
                int newCol = column + dc;
                if (newRow >= 0 && newRow < ocean.getShipArray().length &&
                        newCol >= 0 && newCol < ocean.getShipArray()[0].length &&
                        !(ocean.getShipArray()[newRow][newCol] instanceof EmptySea)) {
                    return false;
                }
            }
        }

        return true; // Position is valid if all checks pass
    }


    // Shoot at a part of the ship
    public boolean shootAt(int row, int column) {
        if (isSunk()) {
            return false;
        }

        int index = horizontal ? column - bowColumn : row - bowRow;
        if (index >= 0 && index < length) {
            this.hit[index] = true;
            return true;
        }

        return false;
    }




    // Check if the ship is sunk
    public boolean isSunk() {
        for (boolean partHit : this.hit) {
            if (!partHit) {
                return false; // Return false if any part is not hit
            }
        }
        return true; // Return true if all parts are hit
    }


    // String representation of the ship's state
    @Override
    public String toString() {
        return isSunk() ? "x" : "S";
    }
}
