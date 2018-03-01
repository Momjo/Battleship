
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BattleShip_Logic {

    private final List<List<BattleChar>> battleFeld;
    private final Set<Coordinates> previousMoves = new HashSet<>();

    public BattleShip_Logic(List<List<BattleChar>> battleFeld)  {
        this.battleFeld = battleFeld;
    }

    public BattleChar get(Coordinates coordinates) {
        return battleFeld.get(coordinates.row).get(coordinates.column);
    }

    public boolean hasPreviouslyShotAt(Coordinates coordinates) {
        return previousMoves.contains(coordinates);
    }

    public boolean hasPreviouslyShotAt(Set<Coordinates> coordinates) {
        return previousMoves.containsAll(coordinates);
    }

    public Set<Coordinates> hitsEntireShipSunk(Coordinates c, Player target) {
        Set<Coordinates> result = new HashSet<>();
        result.add(c);

        if (target.hasShipAt(c) && !hasPreviouslyShotAt(c)) {
            return null;
        }

        Set<Coordinates> right = isShipSunkToTheRight(c, target);
        Set<Coordinates> left = isShipSunkToTheLeft(c, target);
        Set<Coordinates> up = isShipSunkToTheUp(c, target);
        Set<Coordinates> down = isShipSunkToTheDown(c, target);

        if (right != null && left != null && up != null && down != null) {

            result.addAll(right);
            result.addAll(left);
            result.addAll(up);
            result.addAll(down);

            return result;
        }

        return null;
    }

    // write a test for this method !!!!!

    public void markFieldAsShotAt(Coordinates coordinates) {
        previousMoves.add(coordinates);
    }

    public void markAdjacentFieldsAsShotAt(Set<Coordinates> coodinates) {

        for (Coordinates cc: coodinates) {
            int rowUp = cc.row;
            int rowDown = cc.row;
            int colRight = cc.column;
            int colLeft = cc.column;

            if (cc.row - 1 != -1) {
                rowUp = rowUp - 1;
            }
            if (cc.row + 1 != 10) {
                rowDown = rowDown + 1;
            }
            if (cc.column - 1 != -1) {
                colLeft = colLeft - 1;
            }
            if (cc.column + 1 != 10) {
                colRight = colRight + 1;
            }

            Coordinates rightFeld = new Coordinates(cc.row, colRight);
            Coordinates leftFeld = new Coordinates(cc.row, colLeft);
            Coordinates upFeld = new Coordinates(rowUp, cc.column);
            Coordinates downFeld = new Coordinates(rowDown, cc.column);
            Coordinates rightUp = new Coordinates(rowUp, colRight);
            Coordinates rightDown = new Coordinates(rowDown, colRight);
            Coordinates leftUp = new Coordinates(rowUp, colLeft);
            Coordinates leftDown = new Coordinates(rowDown, colLeft);


            previousMoves.add(rightFeld);
            previousMoves.add(leftFeld);
            previousMoves.add(upFeld);
            previousMoves.add(downFeld);
            previousMoves.add(rightUp);
            previousMoves.add(rightDown);
            previousMoves.add(leftUp);
            previousMoves.add(leftDown);
        }
    }

    private Set<Coordinates> isShipSunkToTheRight(Coordinates c, Player target) {
        Set<Coordinates> result = new HashSet<>();

        int col = c.column;

        while (true) {
            col++;
            Coordinates cor = new Coordinates(c.row, col);
            if (col == 10) {
                return result;
            } else if (target.isFieldEmpty(c)) {
                return result;
            } else if (target.hasShipAt(c) && !previousMoves.contains(cor)) {
                return null;
            }
            result.add(cor);
        }
    }

    private Set<Coordinates> isShipSunkToTheLeft(Coordinates c, Player target) {
        Set<Coordinates> result = new HashSet<>();

        int col = c.column;
        while (true) {
            col--;
            Coordinates cor = new Coordinates(c.row, col);
            if (col == -1) {
                return result;
            } else if (target.isFieldEmpty(cor)) {
                return result;
            } else if (target.hasShipAt(cor) && !previousMoves.contains(cor)) {
                return null;
            }
            result.add(cor);
        }
    }

    private Set<Coordinates> isShipSunkToTheUp(Coordinates c, Player target) {
        Set<Coordinates> result = new HashSet<>();

        int row = c.row;
        while (true) {
            row--;
            Coordinates cor = new Coordinates(row, c.column);
            if (row == -1) {
                return result;
            } else if (target.isFieldEmpty(cor)) {
                return result;
            } else if (target.hasShipAt(cor) && !previousMoves.contains(cor)) {
                return null;
            }
            result.add(cor);
        }
    }

    private Set<Coordinates> isShipSunkToTheDown(Coordinates c, Player target) {

        Set<Coordinates> result = new HashSet<>();
        int row = c.row;
        while (true) {
            row++;
            Coordinates cor = new Coordinates(row, c.column);
            if (row == 10) {
                return result;
            } else if (target.isFieldEmpty(cor)) {
                return result;
            } else if (target.hasShipAt(cor) && !previousMoves.contains(cor)) {
                return null;
            }
            result.add(cor);
        }
    }


}

