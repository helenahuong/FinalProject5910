public class Battleship extends Ship {

    public Battleship() {
        this.length = 4; // Correct length for a Battleship
        this.hit = new boolean[this.length]; // Initialize hit array with correct size
    }

    @Override
    public String getShipType() {
        return "Battleship";
    }

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Check bounds
        if (horizontal) {
            if (column + this.length > ocean.getShipArray()[0].length) return false;
        } else {
            if (row + this.length > ocean.getShipArray().length) return false;
        }

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= this.length; dc++) {
                int checkRow = horizontal ? row + dr : row + dc;
                int checkColumn = horizontal ? column + dc : column + dr;

                if (checkRow < 0 || checkRow >= ocean.getShipArray().length ||
                        checkColumn < 0 || checkColumn >= ocean.getShipArray()[0].length) {
                    continue; // Skip checks outside the ocean
                }

                if (!(ocean.getShipArray()[checkRow][checkColumn] instanceof EmptySea)) {
                    return false; // Ship or adjacent position is not empty
                }
            }
        }

        return true;
    }

    private boolean isPositionClear(int row, int column, Ocean ocean) {
        return row >= 0 && row < ocean.getShipArray().length &&
                column >= 0 && column < ocean.getShipArray()[0].length &&
                ocean.getShipArray()[row][column] instanceof EmptySea;
    }

    private boolean areAdjacentPositionsClear(int row, int column, Ocean ocean) {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue; // Skip the ship's own position
                if (!isPositionClear(row + dr, column + dc, ocean)) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.bowRow = row;
        this.bowColumn = column;
        this.horizontal = horizontal;

        for (int i = 0; i < this.length; i++) {
            if (horizontal) {
                ocean.getShipArray()[row][column + i] = this;
            } else {
                ocean.getShipArray()[row + i][column] = this;
            }
        }
    }


}