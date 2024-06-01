public class Submarine extends Ship {

    public Submarine() {
        this.length = 1;
        this.hit = new boolean[1];
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }

    @Override
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.bowRow = row;
        this.bowColumn = column;
        this.horizontal = horizontal;

        ocean.getShipArray()[row][column] = this;
    }

    @Override
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Check if the ship goes out of bounds
        if (horizontal) {
            if (column + this.length > ocean.getShipArray()[0].length) {
                return false;
            }
        } else {
            if (row + this.length > ocean.getShipArray().length) {
                return false;
            }
        }

        // Check for overlap and adjacency with other ships
        for (int i = 0; i < this.length; i++) {
            int currentRow = horizontal ? row : row + i;
            int currentColumn = horizontal ? column + i : column;

            // Check if current position is valid
            if (!isPositionValid(currentRow, currentColumn, ocean)) {
                return false;
            }
        }

        return true;
    }

    private boolean isPositionValid(int row, int column, Ocean ocean) {
        // Check if the position is within the ocean bounds
        if (row < 0 || row >= ocean.getShipArray().length ||
                column < 0 || column >= ocean.getShipArray()[0].length) {
            return false;
        }

        // Check for overlap with other ships or adjacency
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int checkRow = row + dr;
                int checkColumn = column + dc;

                // Skip the checks if it's outside the ocean bounds
                if (checkRow < 0 || checkRow >= ocean.getShipArray().length ||
                        checkColumn < 0 || checkColumn >= ocean.getShipArray()[0].length) {
                    continue;
                }

                // Check if the adjacent cell is not an EmptySea (i.e., another ship is present)
                if (!(ocean.getShipArray()[checkRow][checkColumn] instanceof EmptySea)) {
                    return false;
                }
            }
        }

        return true; // The position is valid if all checks pass
    }

}
