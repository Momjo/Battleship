import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ShipPlacer {


    private List<List<BattleChar>> battleFeld = new ArrayList<>();
    private List<BattleChar> line;

    private Scanner scanner = new Scanner(System.in);

    public BattleChar get(Coordinates coordinates) {

        return battleFeld.get(coordinates.row).get(coordinates.column);

    }


    public List<List<BattleChar>> askPlayerForShips() {

        for (int row = 0; row < 10; row++) {

            line = new ArrayList<>();
            for (int column = 0; column < 10; column++) {
                line.add(column, BattleChar.leer);
            }
            battleFeld.add(row, line);
        }

        setShiponBoard();
        return battleFeld;
    }


    private int dieschiffeImSpiel() {
        int anzahl_Schlachtschiff = 1;
        int anzahl_Kreuzer = 2;
        int anzahl_Zerstörer = 3;
        int anzahl_U_Boote = 4;

        int kästchen = 0;


        System.out.println("So viele Schiffe sind übrig:\n" +
                anzahl_Schlachtschiff + "\tSchlachtschiff( 5 kästchen )\n" +
                anzahl_Kreuzer + "\tKreuzer( 4 Kästchen)\n" +
                anzahl_Zerstörer + "\tZerstörer( 3 kästchen )\n" +
                anzahl_U_Boote + "\tU-Boot( 2 kästchen )\n"
        );
        System.out.println("Was für ein Schiff soll eingefügt werden soll:");
        int ausgewähltes_Schiff = scanner.nextInt();

        if (ausgewähltes_Schiff == 1) {
            anzahl_Schlachtschiff = anzahl_Schlachtschiff - 1;
            kästchen = kästchen + 5;
        } else if (ausgewähltes_Schiff == 2) {
            anzahl_Kreuzer = anzahl_Kreuzer - 1;
            kästchen = kästchen + 4;

        } else if (ausgewähltes_Schiff == 3) {
            anzahl_Zerstörer = anzahl_Zerstörer - 1;
            kästchen = kästchen + 3;

        } else if (ausgewähltes_Schiff == 4) {
            anzahl_U_Boote = anzahl_U_Boote - 1;
            kästchen = kästchen + 2;

        }

        return kästchen;
    }


    private Coordinates shiffStartPunkt() {

        String pattern = "(^[a-j][0-9]$)";
        boolean isMatch;
        String move;
        do {
            System.out.println("Wo Soll das Schiff Startpunkt sein?");
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


    private boolean checkIfThereIsNoShip(Coordinates coordinates) {
        int rowUp = coordinates.row;
        int rowDown = coordinates.row;
        int colRight = coordinates.column;
        int colLeft = coordinates.column;

        if (coordinates.row - 1 != -1) {
            rowUp = rowUp - 1;
        }
        if (coordinates.row + 1 != 10) {
            rowDown = rowDown + 1;
        }
        if (coordinates.column - 1 != -1) {
            colLeft = colLeft - 1;
        }
        if (coordinates.column + 1 != 10) {
            colRight = colRight + 1;
        }

        Coordinates rightFeld = new Coordinates(coordinates.row, colRight);
        Coordinates leftFeld = new Coordinates(coordinates.row, colLeft);
        Coordinates upFeld = new Coordinates(rowUp, coordinates.column);
        Coordinates downFeld = new Coordinates(rowDown, coordinates.column);
        Coordinates rightUp = new Coordinates(rowUp, colRight);
        Coordinates rightDown = new Coordinates(rowDown, colRight);
        Coordinates leftUp = new Coordinates(rowUp, colLeft);
        Coordinates leftDown = new Coordinates(rowDown, colLeft);

        return get(rightFeld) != BattleChar.X
                && get(leftFeld) != BattleChar.X
                && get(upFeld) != BattleChar.X
                && get(downFeld) != BattleChar.X
                && get(rightUp) != BattleChar.X
                && get(rightDown) != BattleChar.X
                && get(leftUp) != BattleChar.X
                && get(leftDown) != BattleChar.X;

    }

    private void addShip() {
        int a = dieschiffeImSpiel();
        Coordinates dd = shiffStartPunkt();

        if (checkIfThereIsNoShip(dd)) {
            for (int i = 0; i < a; i++) {

                BattleChar set = battleFeld.get(dd.row).set(dd.column, BattleChar.X);
                int colLeft = dd.column +1;
                dd = new Coordinates(dd.row, colLeft);
            }
        }


    }


    private void setShiponBoard() {
        int gesamt_des_Schiffes = 10;
        for (int i = 0; i < gesamt_des_Schiffes; i++) {
            addShip();
            printBoard();
        }
    }


    public void printBoard() {
        System.out.println("      A    B    C    D    E    F    G    H    I    J");
        System.out.println("------------------------------------------------------ ");
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
        System.out.println("------------------------------------------------------ ");
    }


}

