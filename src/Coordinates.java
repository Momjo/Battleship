public class Coordinates {
    public final int row;
    public final int column;

    public Coordinates(int row, int column) {

        this.row = row;
        this.column = column;
    }

    public String toString() {
        return this.row + ":" + this.column;
    }

    public boolean equals​(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Coordinates)) {
            return false;
        }

        Coordinates coordinates = (Coordinates) obj;
        boolean same_row = this.row == coordinates.row;
        boolean same_column = this.column == coordinates.column;

        return same_column && same_row;
    }

}
