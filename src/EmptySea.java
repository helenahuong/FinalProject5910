public class EmptySea extends Ship {

    public EmptySea() {
        this.length = 1;
        this.hit = new boolean[this.length];
    }

    @Override
    public String getShipType() {
        return "empty";
    }

    @Override
    public boolean shootAt(int row, int column) {
        return false; // Shooting at an empty sea always misses
    }

    @Override
    public boolean isSunk() {
        return false; // An empty sea cannot be sunk
    }

    @Override
    public String toString() {
        return "-";
    }

}
