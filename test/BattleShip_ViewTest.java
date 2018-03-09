import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class BattleShip_ViewTest {
    private Player player_a;
    private Player player_b;

    {
        try {
            List<List<BattleChar>> battleFeld_a = new BattleFeldFileLoader().loadFromFile("/Users/mohammad/Documents/GitHub/BattleShip/src/feldBattle");
            List<List<BattleChar>> battleFeld_b = new BattleFeldFileLoader().loadFromFile("/Users/mohammad/Documents/GitHub/BattleShip/src/feldBattle");
            BattleShip_Logic logic_a = new BattleShip_Logic(battleFeld_a);
            BattleShip_Logic logic_b = new BattleShip_Logic(battleFeld_b);

            player_a = new Player(logic_a);
            player_b = new Player(logic_b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    BattleShip_View vv = new BattleShip_View(player_a, player_b, player_a.rows(), player_a.columns());

    @Test
    @DisplayName("Print Board")
    void pb() {
        vv.printBoard(player_a, player_b);
    }

    @Test
    @DisplayName("check board with X")
    void pbWithX()  {
        Coordinates cor = new Coordinates(0, 3);
        player_a.markFieldAsShotAt(cor);
        assertNotEquals(BattleChar.X, vv.printPlayerCell(cor, player_a, player_b));

    }

    @Test
    @DisplayName("check board with Leer")
    void pbWithleer()  {
        Coordinates cor = new Coordinates(0, 0);
        player_a.markFieldAsShotAt(cor);
        assertEquals("®", vv.printPlayerCell(cor, player_a, player_b));

    }

    @Test
    @DisplayName("String to Coordinates")
    void stc() {
        String str = "d0";
        Coordinates ccc = new Coordinates(0,3);
        assertEquals(ccc, vv.stringToCoordinates(str));

    }
    @Test
    @DisplayName("Do Move")
    void doMove() {
        Coordinates ccc = new Coordinates(0,3);
        assertTrue(vv.doMove(player_a, player_b, ccc));
        assertTrue(player_a.hasPreviouslyShotAt(ccc));

    }
    @Test
    @DisplayName("ist shiff in den richtungen gesunken")
    void playername() {
        Set<Coordinates> result = new HashSet<>();
        Coordinates ccc = new Coordinates(3,0);
        result.add(ccc);
        //assertNotEquals(result, player_a.isShipSunkToTheRight(ccc, player_b));
    }
}