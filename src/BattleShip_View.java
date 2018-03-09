

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;


public class BattleShip_View {

    Scanner scanner = new Scanner(System.in);

    private Player player_a;
    private Player player_b;

    private int fieldRows;
    private int fieldColumns;


    public BattleShip_View(Player player_a, Player player_b, int fieldRows, int fieldColumns) {

        this.player_a = player_a;
        this.player_b = player_b;
        this.fieldRows = fieldRows;
        this.fieldColumns = fieldColumns;

    }


    public void printBoard(Player shooter, Player target) {

        String boardBorder = "------------------------------------------------------ ";
        String header = "      A    B    C    D    E    F    G    H    I    J";


        System.out.println(header);
        System.out.println(boardBorder);
        int zahl = -1;
        for (int row = 0; row < this.fieldRows; row++) {
            zahl++;
            System.out.print(zahl + " | ");
            for (int column = 0; column < this.fieldColumns; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                String s = printPlayerCell(coordinates, shooter, target);
                System.out.print("  " + s + "  ");
                if (column == 9) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println(boardBorder);


    }

    public String printPlayerCell(Coordinates coordinates, Player shooter, Player target) {
        if (shooter.hasPreviouslyShotAt(coordinates)) {
            if (target.hasShipAt(coordinates)) {
                return "X";
            } else {
                return "®";
            }
        } else {
            return " ";
        }
    }


    private void updatePlayerName(Player p) {
        System.out.print("Name der erste Spieler: ");
        p.name = scanner.next();
    }


    public Coordinates stringToCoordinates(String move) {

        int column = Character.getNumericValue(move.charAt(0)) - this.fieldColumns;
        int row = Character.getNumericValue(move.charAt(1));
        return new Coordinates(row, column);
    }

    public Coordinates getPlayerMove(Player player) {

        String pattern = "(^[a-j][0-9]$)";
        boolean isMatch;
        String move;

        do {
            System.out.println("Set your move(A-J)(0-9): ");
            move = scanner.next();
            isMatch = Pattern.matches(pattern, move);
            if (isMatch == false) {
                System.out.println("Wrong, try again :)");

            } else if (player.hasPreviouslyShotAt(stringToCoordinates(move))) {
                System.out.println("du hast da schon geschossen\n");
                isMatch = false;
            }
        } while (!isMatch);
        Coordinates coordinates = stringToCoordinates(move);
        return coordinates;
    }

    public boolean doMove(Player shooter, Player target, Coordinates coordinates) {
        shooter.markFieldAsShotAt(coordinates);

        return target.hasShipAt(coordinates);
    }

    private boolean isGameOver() {
        Set<Coordinates> schiffeImSpiel = new HashSet<>();
        for (int row = 0; row < this.fieldRows; row++) {
            for (int column = 0; column < this.fieldColumns; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                if (this.player_a.hasShipAt(coordinates) || this.player_b.hasShipAt(coordinates)) {
                    schiffeImSpiel.add(coordinates);
                }
            }
        }

        return this.player_a.hasPreviouslyShotAt(schiffeImSpiel) || this.player_b.hasPreviouslyShotAt(schiffeImSpiel);
    }

    private void gameLoop() {
        int zahlt = 0;

        while (!isGameOver()) {
            Player shooter, target;

            if (zahlt % 2 == 0) {
                shooter = player_a;
                target = player_b;
            } else {
                shooter = player_b;
                target = player_a;
            }
            String nameBorderStyle = "******************************************************";
            System.out.println(nameBorderStyle);
            System.out.println("* " + shooter.name + ", your move!");
            System.out.println(nameBorderStyle);
            printBoard(shooter, target);
            Coordinates coordinates = getPlayerMove(shooter);
            boolean getroffen = doMove(shooter, target, coordinates);

            if (getroffen) {
                System.out.println("getroffen");
                Set<Coordinates> shipCoordinates = shooter.hitsEntireShipSunk(coordinates, target);
                if (shipCoordinates != null) {
                    System.out.println("...und versenkt!");
                    shooter.markAdjacentFieldsAsShotAt(shipCoordinates);

                }

            } else {
                System.out.println("nicht getroffen");
                zahlt++;
            }

            printBoard(shooter, target);
        }
    }


    public static void main(String[] args) throws IOException  {

        /*
         * load default Battle field
         */

        List<List<BattleChar>> battleFeld_a = new BattleFeldFileLoader().loadFromFile("src/FirstPlayerBattleField");
        List<List<BattleChar>> battleFeld_b = new BattleFeldFileLoader().loadFromFile("src/SecondPlayerBattleField");


        /*
         * set your custom Battle field
         */

        //List<List<BattleChar>> battleFeld_a = new ShipPlacer().askPlayerForShips();
        //List<List<BattleChar>> battleFeld_b = new ShipPlacer().askPlayerForShips();


        BattleShip_Logic logic_a = new BattleShip_Logic(battleFeld_a);
        BattleShip_Logic logic_b = new BattleShip_Logic(battleFeld_b);

        Player player_a = new Player(logic_a);
        Player player_b = new Player(logic_b);

        BattleShip_View bv = new BattleShip_View(player_a, player_b, player_a.rows(), player_a.columns());

        bv.updatePlayerName(bv.player_a);
        bv.updatePlayerName(bv.player_b);

        bv.gameLoop();

    }

}
