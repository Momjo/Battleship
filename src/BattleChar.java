public enum BattleChar {


    X, leer;

    public String toString() {
        if (this == X) {
            return "X";
        }
        else {
            return " ";
        }
    }

    public static BattleChar unserialize(char c) {
        if (c == '.') {
            return BattleChar.leer;
        } else if (c == 'o') {
            return BattleChar.X;
        } else {
            throw new RuntimeException("Unexpected value!");
        }
    }

    public char serialize() {
        if (this == leer) {
            return '.';
        } else {
            return 'o';
        }
    }
}
