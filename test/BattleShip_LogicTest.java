import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleShip_LogicTest {



    @Test
    @DisplayName("Logic get Method")
    void getMethod() throws IOException {
        List<List<BattleChar>> battleFeld = new BattleFeldFileLoader().loadFromFile("/Users/mohammad/Documents/GitHub/BattleShip/src/feldBattle");

        BattleShip_Logic aa = new BattleShip_Logic(battleFeld);
        Coordinates cc = new Coordinates(0, 0);
        assertEquals(BattleChar.leer , aa.get(cc));
    }


}