import java.util.*;
import java.util.regex.Pattern;

public class ShipPlacer {
    private enum Direction {
        UP, LEFT, DOWN, RIGHT
    }

    private enum Ship {
        SCHLACHTSCHIFF(4), KREUZER(3), ZERSTÖRER(2), UBOOT(1);

        final public int länge;

        Ship(int i) {
            länge = i;
        }
    }

    private final int columns = 10;
    private final int rows = 10;

    private int anzahl_Schlachtschiff = 1;
    private int anzahl_Kreuzer = 2;
    private int anzahl_Zerstörer = 3;
    private int anzahl_U_Boote = 4;


    private List<List<BattleChar>> battleFeld = new ArrayList<>();
    private List<BattleChar> line;

    private Scanner scanner = new Scanner(System.in);

    public BattleChar get(Coordinates coordinates) {

        return battleFeld.get(coordinates.row).get(coordinates.column);

    }


    public List<List<BattleChar>> askPlayerForShips() {
        String nameBorderStyle = "******************************************************";
        System.out.println(nameBorderStyle);
        System.out.println("* " + "create your battle feld!");
        System.out.println(nameBorderStyle);

        for (int row = 0; row < 10; row++) {

            line = new ArrayList<>();
            for (int column = 0; column < 10; column++) {
                line.add(column, BattleChar.leer);
            }
            battleFeld.add(row, line);
        }

        printBoard();
        gamePlayLoop();
        return battleFeld;
    }

    private void gamePlayLoop() {


        while (anzahl_Schlachtschiff + anzahl_Kreuzer + anzahl_Zerstörer + anzahl_U_Boote != 0) {

            System.out.println("So viele Schiffe sind übrig:");
            System.out.println("\t1: Schlachtschiff( 4 kästchen ), anzahl: " + anzahl_Schlachtschiff);
            System.out.println("\t2: Schlachtschiff( 3 kästchen ), anzahl: " + anzahl_Kreuzer);
            System.out.println("\t3: Schlachtschiff( 2 kästchen ), anzahl: " + anzahl_Zerstörer);
            System.out.println("\t4: Schlachtschiff( 1 kästchen ), anzahl: " + anzahl_U_Boote);
            if (!checkToAddShip()) {
                System.out.println("\nSchiff konnte nicht eingefügt werden");

            }
            printBoard();
        }
    }

    private void schiffeAnzahlVermindern(Ship ship) {
        if (ship == Ship.SCHLACHTSCHIFF) {
            anzahl_Schlachtschiff = anzahl_Schlachtschiff - 1;
        } else if (ship == Ship.KREUZER) {
            anzahl_Kreuzer = anzahl_Kreuzer - 1;
        } else if (ship == Ship.ZERSTÖRER) {
            anzahl_Zerstörer = anzahl_Zerstörer - 1;
        } else if (ship == Ship.UBOOT) {
            anzahl_U_Boote = anzahl_U_Boote - 1;
        }
    }

    private boolean checkToAddShip() {
        Ship ship = askToAddShipOnField();

        Coordinates cor = shiffStartPunkt();
        Set<Coordinates> shipCoordinates;


        if (ship.länge == 1) {
            if (areAllAdjacentCellsEmpty(cor)) {

                battleFeld.get(cor.row).set(cor.column, BattleChar.X);
                schiffeAnzahlVermindern(ship);
                return true;
            }
            return false;
        } else {

            Direction richtung = welcheRichtung();
            shipCoordinates = getShipCoordinates(cor, ship.länge, richtung);
            if (shipCoordinates == null) {
                return false;
            } else {

                for (Coordinates c : shipCoordinates) {
                    battleFeld.get(c.row).set(c.column, BattleChar.X);
                }
                schiffeAnzahlVermindern(ship);

                return true;
            }
        }
    }


    private void noShipMore(int a) {
        if ((a == 1 && anzahl_Schlachtschiff == 0) || (a == 2 && anzahl_Kreuzer == 0) || (a == 3 && anzahl_Zerstörer == 0) || (a == 4 && anzahl_U_Boote == 0)) {

            System.out.println("Von dem ausgewälten Schiff ist nichts mehr übrig");
            askToAddShipOnField();
        }
    }

    private Ship askToAddShipOnField() {


        System.out.print("\nWas für ein Schiff soll eingefügt werden soll:");

        int ausgewähltes_Schiff = scanner.nextInt();

        noShipMore(ausgewähltes_Schiff);
        if (ausgewähltes_Schiff == 1) {
            return Ship.SCHLACHTSCHIFF;
        } else if (ausgewähltes_Schiff == 2) {
            return Ship.KREUZER;
        } else if (ausgewähltes_Schiff == 3) {
            return Ship.ZERSTÖRER;
        } else if (ausgewähltes_Schiff == 4) {
            return Ship.UBOOT;
        } else {
            throw new RuntimeException("Bumm!");
        }
    }


    private Coordinates shiffStartPunkt() {

        String pattern = "(^[a-j][0-9]$)";
        boolean isMatch;
        String move;
        do {
            System.out.print("\nWo Soll das Schiff Startpunkt sein?");
            move = scanner.next();
            isMatch = Pattern.matches(pattern, move);
            if (isMatch == false) {
                System.out.println("Wrong, try again :)");

            }
        } while (!isMatch);
        int column = Character.getNumericValue(move.charAt(0)) - 10;
        int row = Character.getNumericValue(move.charAt(1));
        return new Coordinates(row, column);
    }

    private Direction welcheRichtung() {

        System.out.print("\nWelche richtung (l = Left, r = Right, u = Up, d = Down): ");
        char richtung = scanner.next().charAt(0);

        if (richtung == 108) {
            return Direction.LEFT;
        } else if (richtung == 114) {
            return Direction.RIGHT;
        } else if (richtung == 100) {
            return Direction.DOWN;
        } else if (richtung == 117) {
            return Direction.UP;
        } else {
            throw new RuntimeException("Bumm");
        }
    }

    private boolean lookIfThereIsEnoughSpace(Direction direction, Coordinates cor, int length) {
        if (direction == Direction.LEFT) {
            if (cor.column - length > -1) {
                return true;
            }
        }
        if (direction == Direction.RIGHT) {
            if (cor.column + length <= 10) {
                return true;
            }
        }
        if (direction == Direction.UP) {
            if (cor.row - length > -1) {
                return true;
            }
        }
        if (direction == Direction.DOWN) {
            if (cor.row + length <= 10) {
                return true;
            }
        }
        return false;
    }


    private Set<Coordinates> getShipCoordinates(Coordinates start, int length, Direction direction) {
        Set<Coordinates> shipCoordinates = new HashSet<>();
        if (lookIfThereIsEnoughSpace(direction, start, length)) {
            for (int i = 1; i < length; i++) {

                if (areAllAdjacentCellsEmpty(start)) {

                    shipCoordinates.add(start);

                    if (direction == Direction.LEFT) {
                        start = new Coordinates(start.row, start.column - 1);
                    }
                    if (direction == Direction.RIGHT) {
                        start = new Coordinates(start.row, start.column + 1);
                    }
                    if (direction == Direction.UP) {
                        start = new Coordinates(start.row - 1, start.column);
                    }
                    if (direction == Direction.DOWN) {
                        start = new Coordinates(start.row + 1, start.column);
                    }
                } else {
                    return null;
                }
            shipCoordinates.add(start);
            }
        } else {
            return null;
        }
        return shipCoordinates;
    }

    private boolean areAllAdjacentCellsEmpty(Coordinates coordinates) {


        int corUp = coordinates.row;
        int corDown = coordinates.row;
        int corLeft = coordinates.column;
        int corRight = coordinates.column;


        if (coordinates.row - 1 != -1) {
            corUp = corUp - 1;
        }
        if (coordinates.row + 1 != 10) {
            corDown = corDown + 1;
        }
        if (coordinates.column - 1 != -1) {
            corLeft = corLeft - 1;
        }
        if (coordinates.column + 1 != 10) {
            corRight = corRight + 1;
        }

        Coordinates rightFeld = new Coordinates(coordinates.row, corRight);
        Coordinates leftFeld = new Coordinates(coordinates.row, corLeft);

        Coordinates upFeld = new Coordinates(corUp, coordinates.column);
        Coordinates downFeld = new Coordinates(corDown, coordinates.column);

        Coordinates rightUp = new Coordinates(corUp, corRight);
        Coordinates rightDown = new Coordinates(corDown, corRight);

        Coordinates leftUp = new Coordinates(corUp, corLeft);
        Coordinates leftDown = new Coordinates(corDown, corLeft);

        return get(rightFeld) != BattleChar.X
                && get(leftFeld) != BattleChar.X
                && get(upFeld) != BattleChar.X
                && get(downFeld) != BattleChar.X
                && get(rightUp) != BattleChar.X
                && get(rightDown) != BattleChar.X
                && get(leftUp) != BattleChar.X
                && get(leftDown) != BattleChar.X
                ;
    }

    public void printBoard() {
        String boardBorder = "----------------------------------------------------- ";
        String header = "      A    B    C    D    E    F    G    H    I    J";
        System.out.println(header);
        System.out.println(boardBorder);
        int zahl = -1;
        for (int row = 0; row < 10; row++) {
            zahl++;
            System.out.print(zahl + " | ");
            for (int column = 0; column < 10; column++) {


                System.out.print("  " + battleFeld.get(row).get(column) + "  ");

                if (column == 9) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println(boardBorder);
    }
}

